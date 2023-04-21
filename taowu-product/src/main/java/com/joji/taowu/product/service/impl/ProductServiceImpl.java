package com.joji.taowu.product.service.impl;


import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.param.*;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.product.service.ProductService;

import java.util.List;

/**
 * 淘物商城商品业务实现层
 */
public class ProductServiceImpl implements ProductService {
    @Override
    public Object promo(String categoryName) {
        return null;
    }

    @Override
    public Object hots(ProductCategoryParam productCategoryParam) {
        return null;
    }

    @Override
    public Object clist() {
        return null;
    }

    @Override
    public Object byCategory(ProductParam productParam) {
        return null;
    }

    @Override
    public Object all(ProductParam productParam) {
        return null;
    }

    @Override
    public Object detail(Integer productID) {
        return null;
    }

    @Override
    public Object pictures(Integer productID) {
        return null;
    }

    @Override
    public List<Product> list() {
        return null;
    }

    @Override
    public Object search(ProductSearchParam productSearchParam) {
        return null;
    }

    @Override
    public List<Product> ids(ProductListParam productListParam) {
        return null;
    }

    @Override
    public void batchNumber(List<ProductNumberParam> productNumberParams) {

    }

    @Override
    public Long categoryCount(Integer categoryId) {
        return null;
    }

    @Override
    public R save(ProductPictureParam productPictureParam) {
        return null;
    }

    @Override
    public R update(Product product) {
        return null;
    }

    @Override
    public R remove(Integer productId) {
        return null;
    }
}
