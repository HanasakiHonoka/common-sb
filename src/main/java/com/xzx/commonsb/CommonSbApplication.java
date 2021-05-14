package com.xzx.commonsb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.xzx.commonsb.mapper")
@EnableScheduling
public class CommonSbApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonSbApplication.class, args);
    }

}
