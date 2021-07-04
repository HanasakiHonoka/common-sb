package com.xzx.commonsb.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDate;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author xzx
 * @since 2021-07-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_movie")
@ApiModel(value="Movie对象", description="")
public class Movie extends BaseEntity implements Serializable{

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "电影名称")
    private String title;

    @ApiModelProperty(value = "上映日期")
    private LocalDate releaseTime;

    @ApiModelProperty(value = "电影时长")
    private Integer duration;

    @ApiModelProperty(value = "电影票房")
    private Float boxoffice;

    @ApiModelProperty(value = "首周票房")
    private Float firstBoxoffice;

    @ApiModelProperty(value = "是否为IP电影")
    private Boolean isIp;

    @ApiModelProperty(value = "是否为续集")
    private Boolean isSequel;

    @ApiModelProperty(value = "是否为网络电影")
    private Boolean isNetwork;

    @ApiModelProperty(value = "豆瓣评分")
    private Float doubanRating;

    @ApiModelProperty(value = "百度指数")
    private Integer baiduIndex;

    @ApiModelProperty(value = "发行公司")
    private String issueCompany;

    @ApiModelProperty(value = "制作公司")
    private String manuCompany;

    @ApiModelProperty(value = "评分人数")
    private Integer ratingNumbers;

    @ApiModelProperty(value = "短评数量")
    private Integer commentNumbers;

    @ApiModelProperty(value = "影评数量")
    private Integer reviewNumbers;

    @ApiModelProperty(value = "电影类型")
    private String type;

    @ApiModelProperty(value = "电影制式")
    private String technology;

    @ApiModelProperty(value = "上映地区")
    private String releaseArea;


}
