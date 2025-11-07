package com.fourthBeam.testFlow.domain.api;


/**
 * 通用业务流程接口
 * @param <T> 流程输入数据类型
 */
public interface BizFlow<T extends FlowData> {

    /** 当前流程唯一标识 */
    String code();

    /** 当前流程调用下一个流程的策略（local/mq/其他） */
    default String invokeStrategyType() {
        return "local";
    }

    /** 执行前的预处理 */
    default void preprocess(T data) {}

    /** 执行前判断是否继续流程 */
    default boolean canProceed(T data) { return true; }

    /** 当前流程核心业务逻辑 */
    void doExecute(T data, FlowResult<?> result);

    /** 判断是否执行下一个流程（返回下一个流程 code，否则 null） */
    default String decideNextFlow(T data) { return null; }

    /** 统一执行入口（模板方法） */
    FlowResult<?> execute(T data, FlowResult<?> result);
}


