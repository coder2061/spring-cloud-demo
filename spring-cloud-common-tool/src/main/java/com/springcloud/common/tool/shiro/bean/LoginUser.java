package com.springcloud.common.tool.shiro.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 登录用户
 *
 * @author jiangyf
 */
@ApiModel
@Data
public class LoginUser implements Serializable {
    private static final long serialVersionUID = 509576192116214625L;

    @ApiModelProperty("用户ID")
    private Integer id;

    @ApiModelProperty("用户名")
    private String username;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("盐值")
    private String salt;

    @ApiModelProperty("姓名")
    private String name;

    @ApiModelProperty("手机")
    private String mobile;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("角色")
    private String roleIds;

    @ApiModelProperty("权限")
    private List<Integer> permissionIds;

    @ApiModelProperty("状态（0：正常；1：冻结；2：作废）")
    private Integer status;

    @ApiModelProperty("登录次数")
    private Integer loginCount;

    @ApiModelProperty("上次登录时间")
    private Date lastLoginTime;

    public LoginUser() {
        super();
    }

    public static LoginUser of(String username, String password) {
        LoginUser user = new LoginUser();
        user.setUsername(username);
        user.setPassword(password);
        return user;
    }

    /**
     * 用户敏感信息过滤
     */
    public LoginUser filter() {
        if (this != null) {
            this.password = null;
            this.salt = null;
        }
        return this;
    }

}
