package com.joji.taowu.admin.service.impl;

import com.joji.taowu.admin.service.OrderService;
import com.joji.taowu.common.client.OrderClient;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.ProductSearchParam;
import com.joji.taowu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台管理订单服务业务实现层
 * */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderClient orderClient;

    @Override
    public Object list(ProductSearchParam productSearchParam) {
        R r = orderClient.adminList(productSearchParam);

        log.info("OrderServiceImpl.list业务结束，结果:{}",r);

        return r;
    }
}
