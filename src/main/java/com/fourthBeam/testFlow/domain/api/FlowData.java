package com.fourthBeam.testFlow.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 所有流程共享的上下文与入参基类
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class FlowData implements Serializable {
    private String flowCode; // 流程标识
    private String key;      // 关键入参
    private String traceId;  // 可选：链路追踪ID
}