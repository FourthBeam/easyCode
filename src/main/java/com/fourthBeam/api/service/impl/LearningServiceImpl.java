package com.fourthBeam.api.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fourthBeam.api.DTO.LearningRequestDTO;
import com.fourthBeam.api.service.LearningService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LearningServiceImpl implements LearningService {


    private static final Logger logger = LoggerFactory.getLogger(LearningServiceImpl.class);

    /**
     * @param learningRequestDTO
     * @return
     */
    @Override
    public JSONObject testAop(LearningRequestDTO learningRequestDTO) {
        logger.debug("111111111111111111111222222222222222222222222");
        return null;
    }
}
