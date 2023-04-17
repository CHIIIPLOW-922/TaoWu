package com.joji.taowu.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
/**
 * 轮播图实体类
 * */
@Data
@TableName("rotation")
public class Rotation  implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("rotation_id")
    private Integer rotationId;
    private String  imgPath;
    private String  describes;
    @JsonProperty("product_id")
    private Integer productId;
    //优先级
    private Integer priority;

}