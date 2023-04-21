package com.joji.taowu.wishlist.controller;

import com.joji.taowu.common.param.WishlistParam;
import com.joji.taowu.wishlist.service.WishlistService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 淘物商城收藏夹功能控制器层
 * */
@RestController
@RequestMapping("wishlist")
public class WishlistController {
    @Resource
    private WishlistService wishlistService;


    @PostMapping("save")
    public Object save(@RequestBody WishlistParam wishlistParam){

        return wishlistService.save(wishlistParam);
    }


    @PostMapping("list")
    public Object list(@RequestBody WishlistParam wishlistParam){

        return wishlistService.list(wishlistParam);
    }

    @PostMapping("remove")
    public Object remove(@RequestBody WishlistParam wishlistParam){

        return wishlistService.remove(wishlistParam);
    }


    /**
     * 根据商品id删除
     * @param productId
     * @return
     */
    @PostMapping("remove/bypid")
    public Object removeByPid(@RequestBody Integer productId){

        return wishlistService.removeByPid(productId);
    }
}
