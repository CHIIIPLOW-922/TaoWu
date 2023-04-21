package com.joji.taowu.search.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.joji.taowu.common.param.ProductSearchParam;
import com.joji.taowu.common.utils.R;
/**
 * 搜索服务业务层
 * */
public interface SearchService {
    /**
     * 商品搜索
     * @param productSearchParam
     * @return
     */
    R search(ProductSearchParam productSearchParam) throws JsonProcessingException;
}
