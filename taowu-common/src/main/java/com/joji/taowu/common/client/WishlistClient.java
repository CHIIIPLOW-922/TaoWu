package com.joji.taowu.common.client;


import com.joji.taowu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "taowu-wishlist")
public interface WishlistClient {

    @PostMapping("/collect/remove/bypid")
    R removeByPID(@RequestBody Integer productId);
}
