package com.springcloud.common.tool.enums;

/**
 * 删除标记
 *
 * @author jiangyf
 */
public enum DeleteFlagEnum {

    N(0, "否"), Y(1, "是");

    private int code;
    private String desc;

    DeleteFlagEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
