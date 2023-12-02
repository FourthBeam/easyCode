package com.fourthBeam.annotation.processor;

/**
 * @description：response增强处理器
 * @author： FourthBeam
 * @create： 2023/12/2 16:59
 */
public interface ERProcessor<T, R> {

    /**
     * @param null
     * @return
     * @author: FourthBeam
     * @date: 2023/12/2 17:14
     * @description: TODO
     */
    T doProcess(T Obj, R rule);
}
