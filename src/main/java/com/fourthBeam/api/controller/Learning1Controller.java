package com.fourthBeam.api.controller;


import com.alibaba.fastjson.JSONObject;
import com.fourthBeam.api.dto.LearningRequestDTO;
import com.fourthBeam.api.model.Employees;
import com.fourthBeam.api.service.LearningService;
import com.fourthBeam.features.doublewrite.mapper.mysql.EmployeesMapper;
import com.fourthBeam.features.doublewrite.mapper.oracle.EmployeesOracleMapper;
import com.fourthBeam.features.responseenhancer.annotation.EnhanceResponse;
import com.fourthBeam.shared.injection.annotation.Passed;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class Learning1Controller {

    private final EmployeesMapper employeesMapper;

    private final EmployeesOracleMapper employeesOralceMapper;

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

    @PostMapping("/testMybatis")
    public LearningRequestDTO testMybatis(@RequestBody LearningRequestDTO learningRequestDTO){
        employeesMapper.testUpate("firstname1", "lastname1");
        employeesMapper.testUpate2("firstname2", "lastname2");
        Employees employees = new Employees();
        employees.setFirstName("firstname3");
        employees.setLastName("lastname3");
        employeesMapper.testUpate3(employees);
        Map<String, String> map = new HashMap<>();
        map.put("firstName","firstname4");
        map.put("lastName","lastname4");
        employeesMapper.testUpate4(map);
        return learningRequestDTO;
    }

    @PostMapping("/testMybatisOracle")
    public LearningRequestDTO testMybatisOracle(@RequestBody LearningRequestDTO learningRequestDTO){
        employeesOralceMapper.testUpate("firstname1", "lastname1");
        employeesOralceMapper.testUpate2("firstname2", "lastname2");
        Employees employees = new Employees();
        employees.setFirstName("firstname3");
        employees.setLastName("lastname3");
        employeesOralceMapper.testUpate3(employees);
        Map<String, String> map = new HashMap<>();
        map.put("firstName","firstname4");
        map.put("lastName","lastname4");
        employeesOralceMapper.testUpate4(map);
        return learningRequestDTO;
    }

    public void writeToRedis(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String readFromRedis(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

}
