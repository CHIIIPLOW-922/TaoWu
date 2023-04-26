package com.joji.taowu.admin.controller;


import com.joji.taowu.admin.service.OrderService;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.ProductSearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * 后台管理订单服务控制器
 *
 * */
@RestController
@RequestMapping("admin/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @GetMapping("list")
    public Object list(ProductSearchParam productSearchParam){

        return orderService.list(productSearchParam);
    }
}
