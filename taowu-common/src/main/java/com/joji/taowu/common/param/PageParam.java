package com.joji.taowu.common.param;

import lombok.Data;

import java.io.Serializable;

/**
 * 页面参数类
 * */
@Data
public class PageParam implements Serializable {
    public static final Long serialVersionUID = 1L;

    private int currentPage =1;
    private int pageSize =15;
}
