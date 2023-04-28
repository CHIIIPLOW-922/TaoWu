package com.joji.taowu.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;


/**
 * 淘物商城用户实体
 * */
@Data
@TableName("users")
public class User implements Serializable {

    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    @JsonProperty("user_id")
    private Integer userId;
    private String userName;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPassword;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userPhone;
}
