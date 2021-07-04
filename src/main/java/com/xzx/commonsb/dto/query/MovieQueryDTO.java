package com.xzx.commonsb.dto.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Classname MovieDTO
 * @Description
 * @Date 2021/7/4 21:39
 * @Author XZX
 * @Version 1.0
 */
@Data
public class MovieQueryDTO extends BasePageDTO{

    @ApiModelProperty(value = "电影名称")
    private String title;
}
