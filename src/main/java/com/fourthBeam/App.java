package com.fourthBeam;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.fourthBeam.mapper") // 指定Mapper接口所在的包
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}