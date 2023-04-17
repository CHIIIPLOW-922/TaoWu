package com.joji.taowu.common.param;

import lombok.Data;
/**
 * 商品数量参数类
 * */
@Data
public class ProductNumberParam {

    //商品id
    private Integer productId;
    //购买数量
    private Integer productNum;
}