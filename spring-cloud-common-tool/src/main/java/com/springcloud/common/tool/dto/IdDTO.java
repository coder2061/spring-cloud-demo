package com.springcloud.common.tool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * ID 参数包装对象
 * <p>
 * created by jiangyf on 2019-03-21 20:14
 */
@Data
public class IdDTO {

    @ApiModelProperty(value = "ID")
    @NotNull(message = "ID不能为空")
    private Long id;

}
