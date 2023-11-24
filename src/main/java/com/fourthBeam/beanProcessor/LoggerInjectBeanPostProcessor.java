package com.fourthBeam.beanProcessor;

// 该BeanPostProcessor在Bean对象实例化后检测Bean中是否有一个

// 标注了`@Passed`注解的未赋值的`org.slf4j.Logger`类型的属性

// 如果有，就为这个属性注入值

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;
import com.fourthBeam.annotation.Passed;

import java.util.Arrays;

@Component

public class LoggerInjectBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    private Logger logger = LoggerFactory.getLogger(LoggerInjectBeanPostProcessor.class);


    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        // 获取该Bean的所有属性
        Arrays.stream(bean.getClass().getDeclaredFields())
                // 检测该属性是否能设置成Logger.class的实例
                .filter(f -> f.getType().isAssignableFrom(Logger.class))
                // 检测该属性上是否具有@Passed注解
                .filter(f -> f.getAnnotation(Passed.class) != null)
                // 检测该属性是否尚未被设置
                .filter(f -> {
                    f.setAccessible(true);
                    try {
                        System.out.println("already set check "+f);
                        return f.get(bean) == null;
                    } catch (IllegalAccessException e) {
                        return false;
                    }
                })
                // 尝试设置属性
                .forEach(f -> {
                    logger.debug("Found there's a Logger field annotated with `@Passed`. Bean [" + beanName + "] field name: [" + f.getName() + "].");
                    // 以Passed的value作为logger的name，如果没有就用类全限定名
                    Passed passed = f.getAnnotation(Passed.class);
                    String loggerName = passed.value();
                    if (null == loggerName || loggerName.equals("")) {
                        loggerName = bean.getClass().getName();
                    }
                    try {
                        f.set(bean, LoggerFactory.getLogger(loggerName));
                    } catch (IllegalAccessException e) {
                        logger.warn("Inject logger field! In bean [" + beanName + "], field is [" + f.getName() + "]");
                    }
                });
        return true;
    }
}
