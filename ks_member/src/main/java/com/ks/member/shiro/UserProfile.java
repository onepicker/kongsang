package com.ks.member.shiro;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserProfile implements Serializable {
    private String userMobile;
    private String userId;
    private String userNickname;
    private String userAvatar;
    private String email;

    public String getId(){
        return this.userId;
    }
}

