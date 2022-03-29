package com.ks.member.service.impl;

import com.ks.member.entity.User;
import com.ks.member.mapper.UserMapper;
import com.ks.member.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author dCoder
 * @since 2022-03-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
