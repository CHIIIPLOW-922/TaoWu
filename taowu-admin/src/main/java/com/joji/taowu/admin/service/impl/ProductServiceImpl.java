package com.joji.taowu.admin.service.impl;

import com.joji.taowu.admin.service.ProductService;
import com.joji.taowu.common.client.ProductClient;
import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.param.ProductPictureParam;
import com.joji.taowu.common.param.ProductSearchParam;
import com.joji.taowu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台管理商品服务业务实现层
 *
 * */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Resource
    private ProductClient productClient;

    @Override
    public Object list(ProductSearchParam productSearchParam) {
        R r = productClient.adminList(productSearchParam);

        log.info("ProductServiceImpl.list业务结束，结果:{}",r);
        return r;
    }

    @Override
    @CacheEvict(value = "list.product", allEntries = true)
    public Object save(Product product) {
        //保存 商品和商品图片
        R r = productClient.save(product);
        log.info("ProductServiceImpl.save业务结束，结果:{}",r);
        return r;
    }

    @Override
    @CacheEvict(value = "list.product",allEntries = true)
    @CachePut(value = "product",key = "#product.productId")
    public Object update(Product product) {
        R r = productClient.update(product);
        log.info("ProductServiceImpl.update业务结束，结果:{}",r);
        return r;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = "list.product",allEntries = true),
                    @CacheEvict(value = "product",key = "#product.productId")
            }
    )
    public Object remove(Product product) {
        R r = productClient.remove(product);
        log.info("ProductServiceImpl.remove业务结束，结果:{}",r);
        return r;
    }
}
