package com.joji.taowu.product.controller;


import com.joji.taowu.common.entity.Picture;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.PictureParam;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.product.service.PictureService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 详情页商品图片控制器
 * */
@RestController
@RequestMapping("picture")
public class PictureController {
    @Resource
    private PictureService pictureService;

    @PostMapping("list")
    public R list(@RequestBody PictureParam pictureParam){
        return pictureService.list(pictureParam);
    }

    @PostMapping("add")
    public R add(@RequestBody Picture picture){
        return pictureService.add(picture);
    }

    @PostMapping("remove")
    public R remove(@RequestBody Picture picture){
        return pictureService.remove(picture);
    }
}
