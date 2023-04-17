package com.joji.taowu.common.param;

import lombok.Data;

import java.util.List;
/**
 * 商品集合参数类
 * */
@Data
public class ProductListParam {
    private List<Integer> productList;
}
