package com.joji.taowu.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.joji.taowu.common.entity.Order;
import lombok.Data;
/**
 * 订单显示对象类
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderVO extends Order {
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_picture")
    private String productPicture;
}
