package com.ks.member.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {
    @NotBlank(message = "用户姓名不能为空")
    private String userMobile;

    @NotBlank(message = "密码不能为空")
    private String password;
}
