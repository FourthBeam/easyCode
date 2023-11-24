package com.fourthBeam.api.controller;


import com.alibaba.fastjson.JSONObject;
import com.fourthBeam.api.DTO.LearningRequestDTO;
import com.fourthBeam.api.service.LearningService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class Learning1Controller {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final LearningService lerningService;
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }

    @PostMapping("/testAop")
    public LearningRequestDTO testAop(@RequestBody LearningRequestDTO learningRequestDTO){
        JSONObject jsonObject = lerningService.testAop(learningRequestDTO);
        logger.debug(String.valueOf(jsonObject));
        return learningRequestDTO;
    }

}
