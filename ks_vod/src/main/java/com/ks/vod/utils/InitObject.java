package com.ks.vod.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;

public class InitObject {

    public static DefaultAcsClient initVodClient() throws ClientException {
        String regionId = "cn-shanghai";  // 点播服务接入区域
        DefaultProfile profile = DefaultProfile.getProfile(regionId, ConstProperties.ACCESS_KEY_ID, ConstProperties.ACCESS_KEY_SECRET);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }
}
