package com.joji.taowu.common.param;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.joji.taowu.common.vo.CartVO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
/**
 * 订单参数类
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class OrderParam implements Serializable {
    public static final Long serialVersionUID = 1L;

    @JsonProperty("user_id")
    private Integer userId;
    private List<CartVO> products;
}
