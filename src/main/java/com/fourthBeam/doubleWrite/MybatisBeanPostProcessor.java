package com.fourthBeam.doubleWrite;

import com.fourthBeam.doubleWrite.business.IDomainService;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MybatisBeanPostProcessor implements BeanPostProcessor {

    private static final Logger logger = LoggerFactory.getLogger(MybatisBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        //logger.info("Before Initialization: " + beanName);

        return bean; // 必须返回 Bean，否则 Bean 将不会被注册到 Spring 容器中
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (isTargetBean(bean)) {
            Class<?> anInterface = bean.getClass().getInterfaces()[0];
            String name = anInterface.getName();
            logger.info("========"+name);
            logger.info(beanName+"---"+bean);
            Pair<Object, Object> objectObjectPair = BeanCache.cache.get(name);
            Pair<Object, Object> temp;
            if (objectObjectPair == null){
                if(!beanName.matches("O[A-Z].*")){
                    logger.info("mysql bean1");
                    temp = Pair.of(bean, null);
                }else {
                    logger.info("oracle bean1");
                    temp = Pair.of(null, bean);
                }
            } else {
                if(!beanName.matches("O[A-Z].*")){
                    logger.info("mysql bean2");
                    temp = Pair.of(bean, objectObjectPair.getRight());
                }else {
                    logger.info("oracle bean2");
                    temp = Pair.of(objectObjectPair.getLeft(), bean);
                }
            }
            BeanCache.cache.put(name, temp);

        }
        return bean;
    }

    private boolean isTargetBean(Object bean) {
        return bean instanceof IDomainService;
    }
}