package com.joji.taowu.common.param;

import lombok.Data;

import java.util.List;
/**
 * 商品参数类
 * */
@Data
public class ProductParam {
    private List<Integer> categoryID;
    private int currentPage = 1; //默认值
    private int pageSize = 15; //默认值

}
