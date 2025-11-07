package com.fourthBeam.testFlow.app;


import javax.annotation.PostConstruct;

import com.fourthBeam.testFlow.app.entity.BData;
import com.fourthBeam.testFlow.domain.api.FlowResult;
import com.fourthBeam.testFlow.domain.core.AbstractBizFlow;
import com.fourthBeam.testFlow.domain.core.FlowRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BFlow extends AbstractBizFlow<BData> {

    @Autowired
    private FlowRegistry registry;

    @PostConstruct
    public void register() { registry.register(this); }

    @Override
    public String code() { return "B"; }

    @Override
    public void doExecute(BData data, FlowResult<?> result) {
        System.out.println("[BFlow] executing logic key=" + data.getKey());
        result.setMessage(result.getMessage() + " → B executed");
        //result.setData("Final result from B");
    }
}

