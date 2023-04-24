package com.joji.taowu.admin.controller;


import com.joji.taowu.admin.service.ProductService;
import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.param.ProductPictureParam;
import com.joji.taowu.common.param.ProductSearchParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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


    @GetMapping("list")
    public Object list(ProductSearchParam productSearchParam){

        return productService.list(productSearchParam);
    }

    /**
     * 商品信息保存
     * @param productPictureParam
     * @return
     */
    @PostMapping("save")
    public Object save(ProductPictureParam productPictureParam){
        return productService.save(productPictureParam);
    }


    /**
     * 修改商品信息
     * @param product
     * @return
     */
    @PostMapping("update")
    public Object update(Product product){

        return productService.update(product);
    }


    @PostMapping("remove")
    public Object remove(Product product){

        return productService.remove(product.getProductId());
    }





}
