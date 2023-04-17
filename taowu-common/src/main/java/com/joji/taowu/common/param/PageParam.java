package com.joji.taowu.common.param;

import lombok.Data;

/**
 * 页面参数类
 * */
@Data
public class PageParam {

    private int currentPage = 1;
    private int pageSize = 15;
}
