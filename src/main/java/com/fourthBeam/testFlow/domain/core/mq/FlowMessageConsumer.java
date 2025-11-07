package com.fourthBeam.testFlow.domain.core.mq;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fourthBeam.testFlow.domain.api.FlowData;
import com.fourthBeam.testFlow.domain.core.FlowExecutor;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 消费 MQ 消息并执行对应 Flow
 */
@Component
@RocketMQMessageListener(topic = "${flow.mq.topic:flow-invoke-topic}", consumerGroup = "flow-demo-group")
public class FlowMessageConsumer implements RocketMQListener<String> {

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private FlowExecutor executor;

    @Override
    public void onMessage(String message) {
        try {
            FlowData data = mapper.readValue(message, FlowData.class);
            System.out.println("[MQ] consume message for flow " + data.getFlowCode());
            executor.execute(data.getFlowCode(), data);
        } catch (Exception e) {
            throw new RuntimeException("Consume failed", e);
        }
    }
}
