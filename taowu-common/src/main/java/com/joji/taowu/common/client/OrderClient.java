package com.joji.taowu.common.client;


import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 订单服务Feign标准接口
 * */
@FeignClient(value = "taowu-order")
public interface OrderClient {

    /**
     * 检查商品有没有被引用,有取消删除!
     * @param productId
     * @return
     */
    @PostMapping("/order/check")
    R checkProduct(Integer productId);


    @PostMapping("order/admin/list")
    R adminList(@RequestBody PageParam pageParam);
}
