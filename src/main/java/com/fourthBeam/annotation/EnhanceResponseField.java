package com.fourthBeam.annotation;

import com.fourthBeam.annotation.processor.BO.ERRegexRule;
import com.fourthBeam.annotation.processor.enmus.EnhanceType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description：response增强注解配合的属性注解
 * @author： FourthBeam
 * @create： 2023/12/2 16:18
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnhanceResponseField {

    /**
     * 指定处理器目前
     *
     * @return
     */
    EnhanceType[] enhanceType();

    /**
     * 指定处理器目前
     *
     * @return
     */
    String[] rules() default {};
}
