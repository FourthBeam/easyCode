package com.fourthBeam.testFlow.domain.core.strategy;

import com.fourthBeam.testFlow.domain.api.FlowData;
import com.fourthBeam.testFlow.domain.api.FlowResult;

public interface InvocationStrategy {
    <T extends FlowData> void invoke(String flowCode, T data, FlowResult<?> result);
    String type();
}
