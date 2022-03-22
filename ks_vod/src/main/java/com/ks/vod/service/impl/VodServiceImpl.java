package com.ks.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.ks.vod.service.VodService;
import com.ks.vod.utils.ConstProperties;
import com.ks.vod.utils.InitObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    @Override
    public String upload(MultipartFile file) {

        InputStream in = null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fileName = file.getOriginalFilename();
        String title = fileName.substring(0,fileName.lastIndexOf("."));
        UploadStreamRequest request = new UploadStreamRequest(
                ConstProperties.ACCESS_KEY_ID,
                ConstProperties.ACCESS_KEY_SECRET,
                title,
                fileName,
                in);

        UploadVideoImpl uploadVideo = new UploadVideoImpl();
        UploadStreamResponse response = uploadVideo.uploadStream(request);

        return response.getVideoId();
    }

    @Override
    public void removeVideoById(String id) {
        try {
            DefaultAcsClient client = InitObject.initVodClient();

            DeleteVideoRequest request = new DeleteVideoRequest();

            request.setVideoIds(id);

            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeMoreVideo(List<String> videoList) {
        try {
            DefaultAcsClient client = InitObject.initVodClient();

            DeleteVideoRequest request = new DeleteVideoRequest();

            request.setVideoIds(StringUtils.join(videoList.toArray(),","));
            
            DeleteVideoResponse response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
