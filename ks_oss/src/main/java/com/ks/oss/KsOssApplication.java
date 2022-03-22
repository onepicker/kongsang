package com.ks.oss;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class KsOssApplication {

    public static void main(String[] args) {
        SpringApplication.run(KsOssApplication.class, args);
    }

}
