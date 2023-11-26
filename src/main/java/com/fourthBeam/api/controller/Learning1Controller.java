package com.fourthBeam.api.controller;


import com.alibaba.fastjson.JSONObject;
import com.fourthBeam.annotation.Passed;
import com.fourthBeam.api.BO.Employees;
import com.fourthBeam.api.DTO.LearningRequestDTO;
import com.fourthBeam.api.service.LearningService;
import com.fourthBeam.mapper.EmployeesMapper;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class Learning1Controller {

    private final EmployeesMapper employeesMapper;

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

}
