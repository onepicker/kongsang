package com.ks.vod.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VodService {


    String upload(MultipartFile file);

    void removeVideoById(String id);

    void removeMoreVideo(List<String> videoList);
}
