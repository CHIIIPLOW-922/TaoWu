package com.joji.taowu.common.client;


import com.joji.taowu.common.param.ProductSearchParam;
import com.joji.taowu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 商品搜索服务Feign标准接口
 * */
@FeignClient(value = "taowu-search")
public interface SearchClient {

    /**
     * 搜索服务 商品查询
     * @param productSearchParam
     * @return
     */
    @PostMapping("/search/product")
    R search(@RequestBody ProductSearchParam productSearchParam);
}
