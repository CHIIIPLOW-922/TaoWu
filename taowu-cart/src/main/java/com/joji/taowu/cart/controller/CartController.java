package com.joji.taowu.cart.controller;

import com.joji.taowu.cart.service.CartService;
import com.joji.taowu.common.param.CartParam;
import com.joji.taowu.common.utils.R;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 购物车服务控制器层
 * */
@RestController
@RequestMapping("cart")
public class CartController {

    @Resource
    private CartService cartService;

    @PostMapping("save")
    public R save(@RequestBody CartParam cartParam){

        return cartService.save(cartParam);
    }

    @PostMapping("list")
    public R list(@RequestBody CartParam cartParam){

        return cartService.list(cartParam);
    }


    @PostMapping("update")
    public R update(@RequestBody CartParam cartParam){

        return cartService.update(cartParam);
    }


    @PostMapping("remove")
    public R remove(@RequestBody CartParam cartParam){

        return cartService.remove(cartParam);
    }


    /**
     * 检查是否存在对应商品
     * @param productId
     * @return
     */
    @PostMapping("check")
    public R check(@RequestBody Integer productId){

        return cartService.check(productId);
    }
}
