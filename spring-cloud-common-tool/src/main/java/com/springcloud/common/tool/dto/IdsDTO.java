package com.springcloud.common.tool.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * ID 集合参数包装对象
 * <p>
 * created by jiangyf on 2019-03-21 20:14
 */
@Data
public class IdsDTO {

    @ApiModelProperty(value = "主键")
    @NotEmpty(message = "ID不能为空")
    private List<Long> ids;

    @ApiModelProperty(value = "操作者ID", hidden = true)
    private Integer userId;

    @ApiModelProperty(value = "操作者名称", hidden = true)
    private String userName;

}
