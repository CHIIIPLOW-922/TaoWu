package com.joji.taowu.category.controller;


import com.joji.taowu.category.service.CategoryService;
import com.joji.taowu.common.entity.Category;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.PictureParam;
import com.joji.taowu.common.param.ProductCategoryParam;
import com.joji.taowu.common.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 淘物商城商品分类控制器层
 * */
@RestController
@RequestMapping("category")
public class CategoryController {

    @Resource
    CategoryService categoryService;

    @GetMapping("/{categoryName}")
    public Category detail(@PathVariable(value = "categoryName")String categoryName){

        return categoryService.detail(categoryName);
    }


    @PostMapping("/names")
    public List<Integer> names(@RequestBody ProductCategoryParam productCategoryParam){

        return categoryService.names(productCategoryParam);
    }


    /**
     * 后台管理调用服务
     * @param pictureParam
     * @return
     */
    @PostMapping("admin/list")
    public R pageList(@RequestBody PictureParam pictureParam){

        return categoryService.page(pictureParam);
    }


    @PostMapping("admin/update")
    public R update(@RequestBody Category category){

        return categoryService.update(category);
    }


    @PostMapping("admin/remove")
    public R remove(@RequestBody Category category){

        return categoryService.remove(category);
    }


    @PostMapping("admin/save")
    public R save(@RequestBody Category category){

        return categoryService.save(category);
    }
}
