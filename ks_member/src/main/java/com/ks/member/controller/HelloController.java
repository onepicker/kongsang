package com.ks.member.controller;


import com.atguigu.commonutils.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member")
public class HelloController {

    @GetMapping("/hello")
    public R hello(){
        System.out.println("Hello World");
        return R.ok().data("hello","Hello World");
    }
}
