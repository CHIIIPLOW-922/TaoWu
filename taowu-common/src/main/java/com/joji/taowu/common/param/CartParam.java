package com.joji.taowu.common.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * 购物车参数类
 * */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CartParam {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
    private Integer num;
}
