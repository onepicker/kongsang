package com.ks.buy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.ks.buy.mapper")
@SpringBootApplication
public class KsBuyApplication {

    public static void main(String[] args) {
        SpringApplication.run(KsBuyApplication.class, args);
    }

}
