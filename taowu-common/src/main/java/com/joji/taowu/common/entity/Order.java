package com.joji.taowu.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
/**
 * 淘物商城订单实体类
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@TableName("order")
public class Order implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer id;
    @JsonProperty("order_id")
    private Long    orderId; //订单编号,选择使用时间戳
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
    @JsonProperty("product_num")
    private Integer productNum;
    @JsonProperty("product_price")
    private BigDecimal productPrice;
    @JsonProperty("order_time")
    private Long    orderTime;
}