package com.joji.taowu.common.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
/**
 * 收藏夹参数类
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WishlistParam {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("product_id")
    private Integer productId;
}
