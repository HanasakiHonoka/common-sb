package com.xzx.commonsb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Classname Config
 * @Description
 * @Date 2021/5/17 23:02
 * @Author XZX
 * @Version 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("config")
public class Config {
    private static final long serialVersionUID = 1L;

    private Integer id;

    private String mode;

    private String origin;

    private Boolean enable;
}
