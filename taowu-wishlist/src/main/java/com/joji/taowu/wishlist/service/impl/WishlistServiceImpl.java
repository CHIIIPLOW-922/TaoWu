package com.joji.taowu.wishlist.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joji.taowu.common.client.ProductClient;
import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.entity.Wishlist;
import com.joji.taowu.common.param.ProductListParam;
import com.joji.taowu.common.param.WishlistParam;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.wishlist.mapper.WishlistMapper;
import com.joji.taowu.wishlist.service.WishlistService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 淘物商城收藏夹业务实现层
 * */
@Slf4j
@Service
public class WishlistServiceImpl implements WishlistService {
    @Resource
    private WishlistMapper wishlistMapper;

    @Resource
    private ProductClient productClient;

    @Override
    public Object save(WishlistParam wishlistParam) {
        //分解参数
        Integer userId = wishlistParam.getUserId();
        Integer productId = wishlistParam.getProductId();
        //数据库查询
        QueryWrapper<Wishlist> queryWrapper
                = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.eq("product_id",productId);
        Long count = wishlistMapper.selectCount(queryWrapper);

        if ( count> 0){
            log.info("CollectServiceImpl.save业务结束，结果:{}",count);
            return R.fail("商品已在收藏夹! 无需二次添加!");
        }
        //实体类封装
        Wishlist wishlist = new Wishlist();
        wishlist.setProductId(productId);
        wishlist.setUserId(userId);
        wishlist.setAddTime(System.currentTimeMillis());
        //数据库插入
        int rows = wishlistMapper.insert(wishlist);
        if (rows > 0){
            return R.ok("商品添加成功"+rows);
        }
        //结果封装
        return R.fail("商品添加失败!");
    }

    @Override
    public Object list(WishlistParam wishlistParam) {
        //获取用户id
        Integer userId = wishlistParam.getUserId();

        //查询商品id
        QueryWrapper<Wishlist> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("user_id",userId);
        queryWrapper.select("product_id");
        List<Object> list = wishlistMapper.selectObjs(queryWrapper);

        //结果封装
        Integer[] idsArray = list.toArray(new Integer[]{});
        List<Integer> ids = new ArrayList<>();
        ids = Arrays.asList(idsArray);

        if (ids.size() == 0){
            log.info("CollectServiceImpl.list业务结束，结果:{}");
            return R.ok(ids);
        }

        //调用商品服务
        ProductListParam productIdsParam = new ProductListParam();
        productIdsParam.setProductList(ids);
        List<Product> productList = productClient.ids(productIdsParam);

        //结果封装
        return R.ok(productList);
    }

    @Override
    public Object remove(WishlistParam wishlistParam) {
        //参数封装
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_id",wishlistParam.getUserId());
        queryWrapper.eq("product_id",wishlistParam.getProductId());

        int rows = wishlistMapper.delete(queryWrapper);
        if (rows>0){
            return R.ok("收藏移除成功!"+rows);

        }
        log.info("CollectServiceImpl.remove业务结束，结果:{}",rows);

        return R.fail("收藏移除失败");


    }

    @Override
    public Object removeByPid(Integer productId) {
        QueryWrapper<Wishlist> queryWrapper = new QueryWrapper<>();

        queryWrapper.eq("product_id",productId);

        int rows = wishlistMapper.delete(queryWrapper);

        return R.ok(rows);
    }
}
