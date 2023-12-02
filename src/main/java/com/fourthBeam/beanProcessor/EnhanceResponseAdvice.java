package com.fourthBeam.beanProcessor;

import com.fourthBeam.annotation.EnhanceResponse;
import com.fourthBeam.annotation.EnhanceResponseField;
import com.fourthBeam.annotation.Passed;
import com.fourthBeam.annotation.processor.ERProcessorUtils;
import com.fourthBeam.annotation.processor.enmus.EnhanceType;
import org.slf4j.Logger;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Arrays;

/**
 * @description：Response增强
 * @author： FourthBeam
 * @create： 2023/12/2 15:51
 */
@RestControllerAdvice
public class EnhanceResponseAdvice implements ResponseBodyAdvice<Object> {

    @Passed()
    private Logger logger;

    /**
     * @param returnType
     * @param converterType
     * @return boolean
     * @author: FourthBeam
     * @date: 2023/12/2 15:52
     * @description: 是否是用了 {@link com.fourthBeam.annotation.EnhanceResponse} 注解
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return Arrays.stream(returnType.getMethodAnnotations()).anyMatch(annotation -> annotation.annotationType().equals(EnhanceResponse.class));
    }


    /**
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return java.lang.Object
     * @author:
     * @date: 2023/12/2 15:54
     * @description: 执行所要做的处理
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // logger.info("====" + String.valueOf(body.getClass()));
        Arrays.stream(body.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(EnhanceResponseField.class))
                .forEach(field -> {
                    EnhanceResponseField annotation = field.getAnnotation(EnhanceResponseField.class);
                    EnhanceType[] enhanceTypes = annotation.enhanceType();
                    Arrays.stream(enhanceTypes)
                            .forEach(enhanceType -> {
                                try {
                                    field.setAccessible(true);
                                    field.set(body,ERProcessorUtils.getImplementations(enhanceType).doProcess(field.get(body), annotation.rules()));
                                } catch (IllegalAccessException e) {
                                    throw new RuntimeException(e);
                                }
                            });
                });
        return body;
    }
}
