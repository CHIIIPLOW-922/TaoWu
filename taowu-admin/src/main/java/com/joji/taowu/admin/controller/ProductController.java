package com.joji.taowu.admin.controller;


import com.joji.taowu.admin.service.ProductService;
import com.joji.taowu.common.param.ProductSearchParam;
import com.joji.taowu.common.utils.R;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;

/**
 * 后台管理商品服务管理控制器
 * */
@RestController
@RequestMapping("/admin/product")
public class ProductController {


    @Resource
    private ProductService productService;


    @GetMapping("list")
    public Object list(ProductSearchParam productSearchParam){

        return productService.list(productSearchParam);
    }





}
