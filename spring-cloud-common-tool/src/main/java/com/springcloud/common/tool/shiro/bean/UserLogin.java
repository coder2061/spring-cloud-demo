package com.springcloud.common.tool.shiro.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 用户登录
 *
 * @author jiangyf
 */
@ApiModel(value = "用户登录")
public class UserLogin implements Serializable {
    private static final long serialVersionUID = 509576192116214635L;

    @ApiModelProperty(value = "登录账号")
    @NotNull(message = "登录账号不能为空")
    private String username;

    @ApiModelProperty(value = "登录密码")
    @NotNull(message = "登录密码不能为空")
    private String password;

    @ApiModelProperty(value = "是否开启记住我功能")
    private Boolean rememberMe;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getRememberMe() {
        if (rememberMe == null) {
            return false;
        }
        return rememberMe;
    }

    public void setRememberMe(Boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}
