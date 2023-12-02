package com.fourthBeam.annotation.processor.impl;

import com.fourthBeam.annotation.processor.BO.ERRegexRule;
import com.fourthBeam.annotation.processor.ERProcessor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description：TODO
 * @author： FourthBeam
 * @create： 2023/12/2 17:05
 */
public class ERRegexProcessor implements ERProcessor<String, ERRegexRule> {

    @Override
    public String doProcess(String sourceStr, ERRegexRule rule) {
        Pattern pattern = Pattern.compile(rule.getPattern());
        Matcher matcher = pattern.matcher(sourceStr);
        return matcher.replaceAll(rule.getReplacement());
    }
}
