package com.fourthBeam.annotation;

import java.lang.annotation.*;

/**
 * @description：Response增强切点
 * @author： FourthBeam
 * @create： 2023/12/2 15:51
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnhanceResponse {

    String value() default "";
}
