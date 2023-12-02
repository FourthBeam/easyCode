package com.fourthBeam.annotation.processor;

import com.fourthBeam.annotation.processor.enmus.EnhanceType;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public  class ERProcessorUtils {
    private static final Map<EnhanceType, ERProcessor> implementations = new ConcurrentHashMap<>();

    public static void addImplementation(EnhanceType key, ERProcessor implementation) {
        implementations.put(key, implementation);
    }

    public static ERProcessor getImplementations(EnhanceType key) {
        return implementations.get(key);
    }

}
