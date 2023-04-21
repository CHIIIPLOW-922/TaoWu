package com.joji.taowu.wishlist.service;


import com.joji.taowu.common.param.WishlistParam;

/**
 * 淘物商城收藏夹业务层
 * */
public interface WishlistService {

    /**
     * 收藏保存服务
     * @param wishlistParam
     * @return
     */
    Object save(WishlistParam wishlistParam);

    /**
     * 查询收藏列表
     * @param wishlistParam
     * @return
     */
    Object list(WishlistParam wishlistParam);

    /**
     * 删除收藏业务
     * @param wishlistParam
     * @return
     */
    Object remove(WishlistParam wishlistParam);

    /**
     * 商品商品id对应的收藏信息
     * @param productId
     * @return
     */
    Object removeByPid(Integer productId);
}
