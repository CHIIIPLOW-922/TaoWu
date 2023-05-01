package com.joji.taowu.admin.controller;


import com.joji.taowu.admin.service.ProductService;
import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.param.ProductPictureParam;
import com.joji.taowu.common.param.ProductSearchParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 后台管理商品服务管理控制器
 * */
@RestController
@RequestMapping("/admin/product")
public class ProductController {


    @Resource
    private ProductService productService;


    @PostMapping("list")
    public Object list(@RequestBody ProductSearchParam productSearchParam){

        return productService.list(productSearchParam);
    }

    @GetMapping("search")
    public Object search(ProductSearchParam productSearchParam){
        return productService.search(productSearchParam);
    }

    /**
     * 商品信息保存
     * @param product
     * @return
     */
    @PostMapping("save")
    public Object save(@RequestBody Product product){
        return productService.save(product);
    }


    /**
     * 修改商品信息
     * @param product
     * @return
     */
    @PostMapping("update")
    public Object update(@RequestBody Product product){

        return productService.update(product);
    }


    @PostMapping("remove")
    public Object remove(@RequestBody Product product){

        return productService.remove(product);
    }





}
