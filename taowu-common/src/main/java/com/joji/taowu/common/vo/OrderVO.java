package com.joji.taowu.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * 订单显示对象类
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderVO {
    @JsonProperty("product_name")
    private String productName;
    @JsonProperty("product_picture")
    private String productPicture;
}
