package com.joji.taowu.common.client;

import com.joji.taowu.common.entity.Category;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.ProductCategoryParam;
import com.joji.taowu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 商品分类服务Feign标准接口
 * */
@FeignClient(value = "taowu-category")
public interface CategoryClient {
    @GetMapping("/category")
    List<Category> list();

    @GetMapping("/category/{categoryName}")
    Category detail(@PathVariable String categoryName);


    @PostMapping("/category/names")
    List<Integer> names(@RequestBody ProductCategoryParam productCategoryParam);


    @PostMapping("/category/admin/list")
    R pageList(@RequestBody PageParam pageParam);


    @PostMapping("/category/admin/update")
    R update(@RequestBody  Category category);

    @PostMapping("/category/admin/remove")
    R remove(@RequestBody Category category);

    @PostMapping("/category/admin/save")
    R save(@RequestBody Category category);
}
