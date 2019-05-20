package com.springcloud.common.tool.exception;

/**
 * 逻辑业务异常
 *
 * created by jiangyf on 2019-04-22
 */
public class BizException extends RuntimeException {
    private final int code;

    /**
     * 异常
     *
     * @param code
     * @param msg
     */
    public BizException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    /**
     * 异常
     *
     * @param code
     * @param msg
     * @param e
     */
    public BizException(int code, String msg, Throwable e) {
        super(msg, e);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
