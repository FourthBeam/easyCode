package com.fourthBeam.testFlow.domain.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlowResult<T> implements Serializable {
    /** 是否成功 */
    private boolean success = true;

    /** 信息（错误或提示） */
    private String message = "OK";

    /** 业务结果对象 */
    private T data;

    /** 工具方法 */
    public static <T> FlowResult<T> ok(T data) {
        FlowResult<T> r = new FlowResult<>();
        r.setSuccess(true);
        r.setData(data);
        return r;
    }

    public static <T> FlowResult<T> fail(String msg) {
        FlowResult<T> r = new FlowResult<>();
        r.setSuccess(false);
        r.setMessage(msg);
        return r;
    }
}
