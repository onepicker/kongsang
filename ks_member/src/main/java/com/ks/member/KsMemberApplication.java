package com.ks.member;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.ks.member.mapper")
@SpringBootApplication
public class KsMemberApplication {

    public static void main(String[] args) {
        SpringApplication.run(KsMemberApplication.class, args);
    }

}
