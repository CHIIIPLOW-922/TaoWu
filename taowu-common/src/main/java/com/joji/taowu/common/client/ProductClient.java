package com.joji.taowu.common.client;


import com.joji.taowu.common.entity.Picture;
import com.joji.taowu.common.entity.Product;
import com.joji.taowu.common.param.PictureParam;
import com.joji.taowu.common.param.ProductListParam;
import com.joji.taowu.common.param.ProductPictureParam;
import com.joji.taowu.common.param.ProductSearchParam;
import com.joji.taowu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 商品服务Feign标准接口
 * */
@FeignClient(value = "taowu-product")
public interface ProductClient {

    /**
     * 商品全部数据调用
     * @return
     */
    @GetMapping("/product/list")
    List<Product> list();

    /**
     * 收藏模块调用
     * @param productListParam
     * @return
     */
    @PostMapping("/product/ids")
    List<Product> ids(@RequestBody ProductListParam productListParam);

    @PostMapping("/product/category/count")
    long count(@RequestBody  Integer categoryId);


    /**
     * 后台管理调用!
     * @param productSearchParam
     * @return
     */
    @PostMapping("/product/search")
    R searchPage(@RequestBody ProductSearchParam productSearchParam);

    @PostMapping("/product/save")
    R save(@RequestBody Product product);

    @PostMapping("/product/update")
    R update(@RequestBody  Product product);

    @PostMapping("product/remove")
    R remove(@RequestBody Product product);


    @PostMapping("picture/list")
    R listPic(@RequestBody PictureParam pictureParam);

    @PostMapping("picture/add")
    R addPic(@RequestBody Picture picture);

    @PostMapping("picture/remove")
    R removePic(@RequestParam Integer productId);
}
