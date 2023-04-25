package com.joji.taowu.admin.controller;

import com.joji.taowu.admin.service.CategoryService;
import com.joji.taowu.common.entity.Category;
import com.joji.taowu.common.param.PageParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * 后台管理商品分类管理控制器
 * */

@RestController
@RequestMapping("/admin/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @GetMapping("/list")
    public Object list(PageParam pageParam){

        return  categoryService.listPage(pageParam);
    }

    @PostMapping("/update")
    public Object update(@RequestBody Category category){

        return categoryService.update(category);
    }


    @PostMapping("/remove")
    public Object remove(@RequestBody Category category){

        return categoryService.remove(category);
    }


    @PostMapping("/save")
    public Object save(@RequestBody Category category){

        return categoryService.save(category);
    }
}
