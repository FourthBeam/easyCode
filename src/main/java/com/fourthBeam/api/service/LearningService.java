package com.fourthBeam.api.service;

import com.alibaba.fastjson.JSONObject;
import com.fourthBeam.api.DTO.LearningRequestDTO;

public interface LearningService {

    JSONObject testAop(LearningRequestDTO  learningRequestDTO);
}
