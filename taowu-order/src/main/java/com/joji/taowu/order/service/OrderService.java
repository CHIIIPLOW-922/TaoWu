package com.joji.taowu.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.joji.taowu.common.entity.Order;
import com.joji.taowu.common.param.OrderParam;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.ProductSearchParam;


/**
 * 订单业务层
 * */
public interface OrderService extends IService<Order> {

    /**
     * 订单保存业务
     * @param orderParam
     * @return
     */
    Object save(OrderParam orderParam);

    /**
     * 订单数据查询业务
     * @param orderParam
     * @return
     */
    Object list(OrderParam orderParam);

    /**
     * 检查订单是否包含要删除的商品
     * @param productId
     * @return
     */
    Object check(Integer productId);

    /**
     * 分页查询订单数据
     * @param productSearchParam
     * @return
     */
    Object adminList(ProductSearchParam productSearchParam);
}
