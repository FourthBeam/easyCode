package com.fourthBeam.annotation.processor.impl;

import com.fourthBeam.annotation.processor.ERProcessor;
import com.fourthBeam.annotation.processor.ERProcessorUtils;
import com.fourthBeam.annotation.processor.enmus.EnhanceType;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description：TODO
 * @author： FourthBeam
 * @create： 2023/12/2 17:05
 */
@Component
public class ERRegexProcessor implements ERProcessor<String, String[]> {

    @Override
    public String doProcess(String sourceStr, String[] rules) {
        Pattern pattern = Pattern.compile(rules[0]);
        Matcher matcher = pattern.matcher(sourceStr);
        return matcher.replaceAll(rules[1]);
    }

    @PostConstruct
    private void init(){
        ERProcessorUtils.addImplementation(EnhanceType.REGEX, this);
    }
}
