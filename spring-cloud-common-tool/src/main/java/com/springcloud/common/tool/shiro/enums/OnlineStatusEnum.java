package com.springcloud.common.tool.shiro.enums;

/**
 * 在线状态
 *
 * @author jiangyf
 * @date 2017年10月11日 下午3:05:13
 */
public enum OnlineStatusEnum {
    ONLINE("online", "在线"),
    HIDDEN("hidden", "隐身"),
    OFFLINE("offline", "离线"),
    LOGOUT("logout", "退出"),
    FORCEOUT("forceout", "强制退出");

    private String code;
    private String desc;

    private OnlineStatusEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

}
