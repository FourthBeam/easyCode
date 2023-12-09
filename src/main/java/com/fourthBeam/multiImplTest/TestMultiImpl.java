package com.fourthBeam.multiImplTest;

import com.fourthBeam.annotation.Passed;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @description：TODO
 * @author： liangping
 * @create： 2023/12/8 21:48
 */
@RestController
@RequiredArgsConstructor
public class TestMultiImpl {

    private final Map<String,Game> myMap;

    private  Map<String,Game> myMap2;

    @Passed()
    private Logger logger;

    private final ApplicationContext applicationContext;
    @GetMapping("/test")
    public void getName(){
        myMap.keySet().forEach(logger::info);
        logger.info("==========");
        myMap.values().stream().map(Game::getName).forEach(logger::info);
        logger.info("-----------");
        myMap2.keySet().forEach(logger::info);
        logger.info("==========");
        myMap2.values().stream().map(Game::getName).forEach(logger::info);
    }

    @PostConstruct
    private void init(){
        myMap2 = applicationContext.getBeansOfType(Game.class);
    }
}
