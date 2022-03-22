package com.ks.community.controller;


import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/community")
public class HelloController {

    @GetMapping("/hello")
    public R hello(){
        System.out.println("Hello World");
        return R.ok().data("hello","Hello World");
    }
}
