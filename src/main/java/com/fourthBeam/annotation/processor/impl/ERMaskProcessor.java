package com.fourthBeam.annotation.processor.impl;

import com.fourthBeam.annotation.processor.ERProcessor;
import com.fourthBeam.annotation.processor.ERProcessorUtils;
import com.fourthBeam.annotation.processor.enmus.EnhanceType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @description：TODO
 * @author： FourthBeam
 * @create： 2023/12/2 17:07
 */
@Component
public class ERMaskProcessor implements ERProcessor <String, String[]> {

    @Override
    public String doProcess(String sourceObj,String[] rules) {
        return null;
    }

    @PostConstruct
    private void init(){
        ERProcessorUtils.addImplementation(EnhanceType.MASK, this);
    }
}
