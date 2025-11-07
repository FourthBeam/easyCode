package com.fourthBeam.testFlow.domain.core;


import com.fourthBeam.testFlow.domain.api.BizFlow;
import com.fourthBeam.testFlow.domain.api.FlowData;
import com.fourthBeam.testFlow.domain.api.FlowResult;
import com.fourthBeam.testFlow.domain.core.strategy.InvocationStrategy;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 模板方法：先执行自身逻辑，再按策略调用下一个流程
 */
public abstract class AbstractBizFlow<T extends FlowData> implements BizFlow<T> {

    @Autowired
    private FlowExecutor executor;

    @Override
    public final FlowResult<?> execute(T data, FlowResult<?> result) {
        preprocess(data);
        if (!canProceed(data)) {
            result.setSuccess(false);
            result.setMessage("Condition not met for flow: " + code());
            return result;
        }

        // ✅ 当前 Flow 自身执行逻辑
        doExecute(data, result);

        // 🔁 调用下一个流程（按策略决定）
        String next = decideNextFlow(data);
        if (next != null) {
            InvocationStrategy strategy = executor.getStrategy(invokeStrategyType());
            strategy.invoke(next, data, result);
        }

        return result;
    }
}

