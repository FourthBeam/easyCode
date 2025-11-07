package com.fourthBeam.testFlow.domain.core.strategy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourthBeam.testFlow.domain.api.FlowData;
import com.fourthBeam.testFlow.domain.api.FlowResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("mqStrategy")
public class RocketMqInvokeStrategy implements InvocationStrategy {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;
    @Autowired
    private ObjectMapper mapper;

    @Value("${flow.mq.topic:flow-invoke-topic}")
    private String topic;

    @Override
    public <T extends FlowData> void invoke(String flowCode, T data, FlowResult<?> result) {
        try {
            data.setFlowCode(flowCode);
            String msg = mapper.writeValueAsString(data);
            rocketMQTemplate.convertAndSend(topic, msg);
            System.out.println("[RocketMqInvokeStrategy] MQ message sent for flow " + flowCode);
        } catch (Exception e) {
            result.setSuccess(false);
            result.setMessage("Send MQ failed: " + e.getMessage());
        }
    }

    @Override
    public String type() {
        return "mq";
    }
}

