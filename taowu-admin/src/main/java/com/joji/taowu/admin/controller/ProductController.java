package com.joji.taowu.admin.controller;


import com.joji.taowu.admin.service.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 后台管理商品服务管理控制器
 * */
@RestController
@RequestMapping("/admin/product")
public class ProductController {


    @Resource
    private ProductService productService;







}
