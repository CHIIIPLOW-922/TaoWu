package com.joji.taowu.admin.service;

import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.param.ProductPictureParam;
import com.joji.taowu.common.param.ProductSearchParam;
/**
 * 后台管理商品业务层
 * */
public interface ProductService {

    /**
     * 商品分页
     * @param productSearchParam
     * @return
     */
    Object list(ProductSearchParam productSearchParam);


    /**
     * 商品分页,关键字分页查询!
     * @param productSearchParam
     */
    Object search(ProductSearchParam productSearchParam);

    /**
     * 保存商品业务!
     *    1.保存商品
     *    2.保存商品图片 [异步]
     *    3.商品缓存数据处理 [注解]
     *    4.添加缓存es处理 [异步]
     * @param product
     * @return
     */
    Object save(Product product);

    /**
     * 修改商品信息
     *   1.修改商品信息
     *   2.清空商品缓存集合
     *   3.更新缓存es处理 [异步]
     * @param product
     * @return
     */
    Object update(Product product);

    /**
     * 删除商品数据
     * @param product
     * @return
     */
    Object remove(Product product);
}
