package com.ks.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class KsVodApplication {

    public static void main(String[] args) {
        SpringApplication.run(KsVodApplication.class, args);
    }

}
