package com.fourthBeam.testFlow.domain.core;

import com.fourthBeam.testFlow.domain.api.BizFlow;
import org.springframework.stereotype.Component;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class FlowRegistry {

    private final ConcurrentHashMap<String, BizFlow<?>> flows = new ConcurrentHashMap<>();

    public void register(BizFlow<?> flow) {
        flows.put(flow.code(), flow);
    }

    public BizFlow<?> get(String code) {
        return flows.get(code);
    }
}


