package com.ks.member.entity.vo;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserVo {
    @NotBlank(message = "手机号不能为空")
    @ApiModelProperty(value = "用户手机号")
    private String userMobile;

    @NotBlank(message = "邮箱不能为空")
    @Email(message = "邮箱格式不正确")
    @ApiModelProperty(value = "用户邮箱")
    private String email;

    @ApiModelProperty(value = "用户密码")
    private String userPassword;

    @ApiModelProperty(value = "用户昵称")
    private String userNickname;

    @ApiModelProperty(value = "性别")
    private String userSex;

    @ApiModelProperty(value = "头像")
    private String userAvatar;

    @ApiModelProperty(value = "个性签名")
    private String userSign;

    @ApiModelProperty(value = "验证码")
    private String code;
}
