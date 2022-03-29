package com.ks.member.utils;


import com.ks.member.shiro.UserProfile;
import org.apache.shiro.SecurityUtils;

public class ShiroUtil {

    public static UserProfile getProfile(){
        return (UserProfile) SecurityUtils.getSubject().getPrincipal();
    }
}
