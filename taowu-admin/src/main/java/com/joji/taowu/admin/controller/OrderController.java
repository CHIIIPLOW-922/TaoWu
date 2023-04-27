package com.joji.taowu.admin.controller;


import com.joji.taowu.admin.service.OrderService;
import com.joji.taowu.common.param.ProductSearchParam;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("list")
    public Object list(@RequestBody ProductSearchParam productSearchParam){

        return orderService.list(productSearchParam);
    }
}
