package com.ks.member.controller;


import cn.hutool.core.lang.Assert;
import cn.hutool.crypto.SecureUtil;
import com.atguigu.commonutils.R;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ks.member.entity.LoginDto;
import com.ks.member.entity.User;
import com.ks.member.entity.vo.UserVo;
import com.ks.member.service.UserLabelService;
import com.ks.member.service.UserService;
import com.ks.member.utils.JwtUtils;
import com.ks.member.utils.MailUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author dCoder
 * @since 2022-03-27
 */
@RestController
@RequestMapping("/member")
public class UserController {

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserService userService;
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    MailUtil mailUtil;

    @Autowired
    UserLabelService userLabelService;
    @CrossOrigin
    @PostMapping("/login")
    public R login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("user_mobile", loginDto.getUserMobile()));
        Assert.notNull(user, "用户不存在");
        if(!user.getUserPassword().equals(SecureUtil.md5(loginDto.getPassword()+user.getSalt()))) {
            return R.error().message("密码错误！");
        }
        String jwt = jwtUtils.generateToken(user.getUserId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-Control-Expose-Headers", "Authorization");

        Map<String, Object> map = new HashMap<>();
        map.put("id",user.getUserId());
        map.put("username",user.getUserMobile());
        map.put("avatar",user.getUserAvatar());
        map.put("email",user.getEmail());
        map.put("mobile",user.getUserMobile());
        return R.ok().data(map);
    }

    // 退出
    @GetMapping("/logout")
    @RequiresAuthentication
    public R logout() {
        SecurityUtils.getSubject().logout();
        return R.ok();
    }

    @GetMapping("/sendEmail")
    public R sendEmail(@RequestParam("email") String email) {
        Random random = new Random();
        String code = String.valueOf(random.nextInt(9000) + 1000);
        //跳过发送的时间，先向客户端返回成功
        CompletableFuture.supplyAsync(() -> mailUtil.sendEmailCode(email, code));

        stringRedisTemplate.opsForValue().set(email, code, 5, TimeUnit.MINUTES);
        return R.ok();
    }

    @PostMapping("register")
    public R register(@RequestBody UserVo uservo){
        R r;
        if(uservo == null|| StringUtils.isEmpty(uservo.getEmail())||StringUtils.isEmpty(uservo.getUserMobile())){
            r = R.error();
            r.setMessage("请填写完整信息");
            return r;
        }
        if (!uservo.getCode().equals(stringRedisTemplate.opsForValue().get(uservo.getEmail()))){
            r = R.error();
            r.setMessage("验证码错误或已过期");
            return r;
        }

        User user = new User();
        BeanUtils.copyProperties(uservo,user);
        String uuid = UUID.randomUUID().toString();
        user.setUserPassword(SecureUtil.md5(user.getUserPassword()+ uuid));
        user.setSalt(uuid);
        user.setFans(0);
        user.setIsDeleted(0);
        user.setIsDisabled(0);
        if(StringUtils.isEmpty(user.getUserAvatar())){
            user.setUserAvatar("https://edu-guli-97.oss-cn-beijing.aliyuncs.com/123456.jpg");
        }
        if(StringUtils.isEmpty(user.getUserNickname())){
            user.setUserNickname(user.getUserMobile());
        }
        boolean save = userService.save(user);
        if (user.getLabelIds().size()>0){
            userLabelService.saveList(user.getUserId(), user.getLabelIds());
        }
        return save?R.ok():R.error();
    }
}

