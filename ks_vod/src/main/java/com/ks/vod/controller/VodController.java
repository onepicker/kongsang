package com.ks.vod.controller;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import com.atguigu.commonutils.R;
import com.ks.vod.service.VodService;
import com.ks.vod.utils.InitObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/vod")
public class VodController {

    @Autowired
    private VodService vodService;

    @PostMapping("fileupload")
    public R fileUpload(MultipartFile file){
        String videoId = vodService.upload(file);
        return R.ok().data("videoId",videoId);
    }

    @DeleteMapping("removeVideo/{id}")
    public R removeVideo(@PathVariable String id){
        vodService.removeVideoById(id);
        return R.ok();
    }

    @DeleteMapping("delete-batch")
    public R deleteBatch(@RequestParam List<String> videoList){

        vodService.removeMoreVideo(videoList);

        return R.ok();
    }

    @GetMapping("getPlayerAuth/{id}")
    public R getPlayAuth(@PathVariable String id) throws ClientException {

        //初始化
        DefaultAcsClient client = InitObject.initVodClient();

        //请求
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(id);

        //响应
        GetVideoPlayAuthResponse response = client.getAcsResponse(request);

        //得到播放凭证
        String playAuth = response.getPlayAuth();

        //返回结果
        return R.ok().message("获取凭证成功").data("playAuth", playAuth);
    }

}
