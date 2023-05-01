package com.joji.taowu.product.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.param.*;
import com.joji.taowu.common.utils.R;

import java.util.List;

/**
 * 淘物商城商品业务层
 * */
public interface ProductService extends IService<Product> {

    /**
     * 类别名称,查询商品集合,最多查询7条
     * @param categoryName
     * @return
     */
    Object promo(String categoryName);

    /**
     * 热门商品查询,最多查询7条
     * @param productCategoryParam
     * @return
     */
    Object hots(ProductCategoryParam productCategoryParam);

    /**
     * 查询类别数据集合!
     * 最多返回12条数据
     * @return
     */
    Object clist();

    /**
     * 类别商品查询 前端传递类别集合
     * @param productParam
     * @return
     */
    Object byCategory(ProductParam productParam);

    /**
     * 全部商品查询,可以进行类别集合数据查询业务复用
     * @param productParam
     * @return
     */
    Object all(ProductParam productParam);

    /**
     * 查询商品详情
     * @param productID 商品id
     * @return
     */
    Object detail(Integer productID);

    /**
     * 查询商品图片
     * @param productID
     * @return
     */
    Object pictures(Integer productID);

    /**
     * 查询全部商品信息
     * @return
     */
    List<Product> list();

    /**
     * 关键字商品搜索
     * @param productSearchParam
     * @return
     */
    Object search(ProductSearchParam productSearchParam);

    /**
     * 查询商品集合
     * @param  productListParam
     * @return
     */
    List<Product> ids(ProductListParam productListParam);

    /**
     * 修改商品库存
     * @param productNumberParams
     */
    void batchNumber(List<ProductNumberParam> productNumberParams);

    /**
     * 类别对应商品数量
     * @param categoryId
     * @return
     */
    Long categoryCount(Integer categoryId);

    /**
     * 保存商品信息
     * @param product
     * @return
     */
    boolean save(Product product);

    /**
     * 商品数据进行更新
     * @param product
     * @return
     */
    R update(Product product);

    /**
     * 移除商品信息
     * @param product
     * @return
     */
    R remove(Product product);


    /**
     * 后台管理系统商品模块查询
     * @param productSearchParam
     * */
    Object adminList(ProductSearchParam productSearchParam);
}
