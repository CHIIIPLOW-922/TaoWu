package com.joji.taowu.common.param;

import lombok.Data;

import java.util.List;
/**
 * 商品类别参数类
 * */
@Data
public class ProductCategoryParam {
    private List<String> categoryName;
}
