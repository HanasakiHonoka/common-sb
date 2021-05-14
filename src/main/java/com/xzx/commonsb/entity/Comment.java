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

    private String res;
}
