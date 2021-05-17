package com.xzx.commonsb.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("comment")
public class Comment {
    private static final long serialVersionUID = 1L;

    private Integer id;
    
    private Integer star;
    
    private String comment;
    
    private String movieName;

    @TableField("`like`")
    private Integer like;

    @TableField("tx_res_3")
    private String txRes3;
    @TableField("tx_res_2")
    private String txRes2;
    @TableField("ali_res")
    private String aliRes;

}
