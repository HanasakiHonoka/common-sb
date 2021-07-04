package com.xzx.commonsb.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname BaseEntity
 * @Description
 * @Date 2021/7/4 21:27
 * @Author XZX
 * @Version 1.0
 */
@Data
public class BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Integer id;
}
