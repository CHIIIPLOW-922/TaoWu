package com.joji.taowu.product.controller;


import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.param.*;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.product.service.ProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 商品控制器层
 * */
@RestController
@RequestMapping("product")
public class ProductController {

    @Resource
    private ProductService productService;


    /**
     * 查询全部商品信息,供search服务更新
     * @return
     */
    @GetMapping("list")
    public List<Product> list(){

        return productService.list();
    }


    /**
     * 供收藏服务使用,根据传入的id,查询商品集合!
     * @return
     */
    @PostMapping("ids")
    public List<Product> list(@RequestBody ProductListParam productListParam){

        return productService.ids(productListParam);
    }



    @PostMapping("promo")
    public Object indexPromo(@RequestBody Map<String,String> params){
        String categoryName = params.get("categoryName");
        return productService.promo(categoryName);
    }


    @PostMapping("hots")
    public Object indexHots(@RequestBody ProductCategoryParam productCategoryParam){

        return productService.hots(productCategoryParam);
    }

    @PostMapping("category/list")
    public  Object categoryList(){

        return productService.clist();
    }


    /**
     * 类别查询
     * @param productParam
     * @return
     */
    @PostMapping("bycategory")
    public Object byCategory(@RequestBody ProductParam productParam){

        return productService.byCategory(productParam);
    }

    /**
     * 查询全部商品,可以复用业务!
     * @param productParam
     * @return
     */
    @PostMapping("all")
    public Object all(@RequestBody ProductParam productParam){

        return productService.all(productParam);
    }


    @PostMapping("detail")
    public Object detail(@RequestBody Map<String,Integer> param){
        Integer productID = param.get("productID");
        return productService.detail(productID);
    }

    @PostMapping("pictures")
    public Object productPictures(@RequestBody Map<String,Integer> param){
        Integer productID = param.get("productID");
        return productService.pictures(productID);
    }


    @PostMapping("search")
    public Object search(@RequestBody ProductSearchParam productSearchParam){

        return productService.search(productSearchParam);
    }

    /**
     * 类别服务调用管理调用
     */
    @PostMapping("/category/count")
    public Long categoryCount(@RequestBody Integer categoryId){

        return productService.categoryCount(categoryId);
    }


    @PostMapping("save")
    public R save(@RequestBody ProductPictureParam productPictureParam){
        return productService.save(productPictureParam);
    }


    @PostMapping("update")
    public R update(@RequestBody Product product){
        return productService.update(product);
    }

    @PostMapping("remove")
    public R remove(@RequestBody Integer productId){

        return productService.remove(productId);
    }
}
