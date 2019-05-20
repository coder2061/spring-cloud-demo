package com.springcloud.common.tool.bean;

import com.springcloud.common.tool.exception.BaseErrorCode;
import com.springcloud.common.tool.util.LogContextReflect;

import java.io.Serializable;

/**
 * 吐出到前端并序列化为 json 的 bean
 *
 * @author jiangyf
 */
public class ResultBean<T> implements Serializable {
    private static final long serialVersionUID = -9075544910442561812L;

    /**
     * 获取上下文requestId
     */
    private String tid = LogContextReflect.getReqeustId();
    /**
     * 返回码
     */
    private int code;
    /**
     * 说明
     */
    private String desc;
    /**
     * 返回数据
     */
    private T data;

    public ResultBean() {
    }

    public ResultBean(BaseErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMsg());
    }

    public ResultBean(int code, String desc) {
        this(code, desc, null);
    }

    public ResultBean(BaseErrorCode errorCode, T data) {
        this(errorCode.getCode(), errorCode.getMsg(), data);
    }

    public ResultBean(int code, String desc, T data) {
        this.code = code;
        this.desc = desc;
        this.data = data;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}