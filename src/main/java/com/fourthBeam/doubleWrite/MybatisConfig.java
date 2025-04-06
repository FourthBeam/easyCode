package com.fourthBeam.doubleWrite;

import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MybatisConfig {

    @Bean
    public Interceptor mybatisDoubleInteceptor() {
        return new MybatisDoubleInteceptor();
    }
}