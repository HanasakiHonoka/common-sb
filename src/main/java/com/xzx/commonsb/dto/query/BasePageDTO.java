package com.xzx.commonsb.dto.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname BasePageDTO
 * @Description
 * @Date 2021/7/4 21:40
 * @Author XZX
 * @Version 1.0
 */
@Data
public class BasePageDTO {

    @ApiModelProperty(value = "pageIndex")
    private Integer pageIndex = 1;

    @ApiModelProperty(value = "pageSize")
    private Integer pageSize = 10;
}
