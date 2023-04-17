package com.joji.taowu.common.param;

import lombok.Data;
/**
 * 商品搜索参数类
 * */
@Data
public class ProductSearchParam {
    private String search;
    private int    currentPage = 1;
    private int    pageSize = 15;

    /**
     * 运算分页起始值
     * @return
     */
    public int getFrom(){
        return (currentPage-1)*pageSize;
    }

    /**
     * 返回查询值
     * @return
     */
    public int getSize(){
        return pageSize;
    }
}
