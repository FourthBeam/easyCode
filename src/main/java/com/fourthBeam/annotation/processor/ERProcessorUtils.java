package com.fourthBeam.annotation.processor;

import com.fourthBeam.annotation.processor.enmus.EnhanceType;
import com.fourthBeam.annotation.processor.impl.ERMaskProcessor;
import com.fourthBeam.annotation.processor.impl.ERRegexProcessor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ERProcessorUtils {
    private static final Map<EnhanceType, Class<? extends ERProcessor>> implementations = new HashMap<>();

    private void addImplementation(EnhanceType key, Class<ERRegexProcessor> implementation) {
        implementations.put(key, implementation);
    }

    public Map<EnhanceType, Class<ERRegexProcessor>> getAllImplementations() {
        return implementations;
    }

    public Class<ERRegexProcessor> getImplementations(EnhanceType key) {
        return implementations.get(key);
    }

    @PostConstruct
    private void init() {
        addImplementation(EnhanceType.REGEX, ERRegexProcessor.class);
        addImplementation(EnhanceType.MASK, ERMaskProcessor.class);
    }
}
