package com.joji.taowu.common.client;

import com.joji.taowu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;


/**
 * 购物车Feign标准接口
 * */
@FeignClient(value = "taowu-cart")
public interface CartClient {

    /**
     * 检查商品有没有被引用,有取消删除!
     * @param productId
     * @return
     */
    @PostMapping("/cart/check")
    R checkProduct(Integer productId);
}
