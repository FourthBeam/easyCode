package com.fourthBeam.api.controller;


import com.alibaba.fastjson.JSONObject;
import com.fourthBeam.annotation.EnhanceResponse;
import com.fourthBeam.annotation.Passed;
import com.fourthBeam.api.BO.Employees;
import com.fourthBeam.api.DTO.LearningRequestDTO;
import com.fourthBeam.api.service.LearningService;
import com.fourthBeam.mapper.EmployeesMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Learning1Controller {

    private final EmployeesMapper employeesMapper;

    private final StringRedisTemplate stringRedisTemplate;

    @Passed()
    private Logger logger;

    private final LearningService lerningService;
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }

    @PostMapping("/testAop")
    public LearningRequestDTO testAop(@RequestBody LearningRequestDTO learningRequestDTO){
        JSONObject jsonObject = lerningService.testAop(learningRequestDTO);
        logger.debug(String.valueOf(jsonObject));

        List<Employees> list = employeesMapper.getAll();

        list.forEach(l->logger.info(String.valueOf(l)));

        return learningRequestDTO;
    }

    @PostMapping("/testRedis")
    public String testRedis(@RequestBody LearningRequestDTO learningRequestDTO){
        writeToRedis(learningRequestDTO.getParam1(),learningRequestDTO.getParam2());
        return readFromRedis(learningRequestDTO.getParam1());
    }

    @PostMapping("/testEnhanceResponse")
    @EnhanceResponse
    public LearningRequestDTO testEnhanceResponse(@RequestBody LearningRequestDTO learningRequestDTO){
        logger.info(String.valueOf(learningRequestDTO));
        return learningRequestDTO;
    }

    public void writeToRedis(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String readFromRedis(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

}
