package com.fourthBeam.testFlow.domain.core;


import com.fourthBeam.testFlow.domain.api.BizFlow;
import com.fourthBeam.testFlow.domain.api.FlowData;
import com.fourthBeam.testFlow.domain.api.FlowResult;
import com.fourthBeam.testFlow.domain.core.strategy.InvocationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 统一执行入口 + 策略分发器
 */
@Component
public class FlowExecutor {

    @Autowired
    private FlowRegistry registry;

    @Autowired
    private ApplicationContext ctx;

    @SuppressWarnings("unchecked")
    public <T extends FlowData> FlowResult<?> execute(String flowCode, T data) {
        BizFlow<T> flow = (BizFlow<T>) registry.get(flowCode);
        if (flow == null) throw new IllegalArgumentException("Flow not found: " + flowCode);

        FlowResult<Object> result = new FlowResult<>();
        return flow.execute(data, result);
    }

    public InvocationStrategy getStrategy(String type) {
        if ("mq".equalsIgnoreCase(type)) {
            return ctx.getBean("mqStrategy", InvocationStrategy.class);
        }
        return ctx.getBean("localStrategy", InvocationStrategy.class);
    }
}