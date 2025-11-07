package com.fourthBeam.testFlow.domain.core.strategy;



import com.fourthBeam.testFlow.domain.api.BizFlow;
import com.fourthBeam.testFlow.domain.api.FlowData;
import com.fourthBeam.testFlow.domain.api.FlowResult;
import com.fourthBeam.testFlow.domain.core.FlowRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("localStrategy")
public class LocalInvokeStrategy implements InvocationStrategy {

    @Autowired
    private FlowRegistry registry;

    @Override
    @SuppressWarnings("unchecked")
    public <T extends FlowData> void invoke(String flowCode, T data, FlowResult<?> result) {
        BizFlow<T> flow = (BizFlow<T>) registry.get(flowCode);
        if (flow != null) {
            flow.execute(data, result);
        }
    }

    @Override
    public String type() { return "local"; }
}