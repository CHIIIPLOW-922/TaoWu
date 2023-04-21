package com.joji.taowu.search.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joji.taowu.common.param.ProductSearchParam;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 搜索服务控制器层
 * */
@RestController
@RequestMapping("search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping("product")
    public R productList(@RequestBody ProductSearchParam productParamsSearch) throws JsonProcessingException {


        return searchService.search(productParamsSearch);
    }
}
