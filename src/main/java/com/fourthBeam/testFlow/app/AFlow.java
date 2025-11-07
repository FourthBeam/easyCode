package com.fourthBeam.testFlow.app;

import com.fourthBeam.testFlow.app.entity.AData;
import com.fourthBeam.testFlow.domain.api.FlowResult;
import com.fourthBeam.testFlow.domain.core.AbstractBizFlow;
import com.fourthBeam.testFlow.domain.core.FlowRegistry;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AFlow extends AbstractBizFlow<AData> {

    @Autowired
    private FlowRegistry registry;

    @PostConstruct
    public void register() { registry.register(this); }

    @Override
    public String code() { return "A"; }

    @Override
    public String invokeStrategyType() {
        return "mq"; // AFlow 通过 MQ 触发下一个流程
    }

    @Override
    public void preprocess(AData data) {
        System.out.println("[AFlow] 参数预处理 userId=" + data.getUserId());
        data.setValidated(data.getAmount() > 0);
    }

    @Override
    public boolean canProceed(AData data) {
        boolean ok = data.isValidated();
        System.out.println("[AFlow] 检查条件: validated=" + ok);
        return ok;
    }

    @Override
    public void doExecute(AData data, FlowResult<?> result) {
        System.out.println("[AFlow] executing logic, userId=" + data.getUserId());
        result.setMessage("A executed");
    }

    @Override
    public String decideNextFlow(AData data) {
        if (data.getAmount() > 100) {
            return "B"; // 决定触发 BFlow
        }
        return null;
    }
}

