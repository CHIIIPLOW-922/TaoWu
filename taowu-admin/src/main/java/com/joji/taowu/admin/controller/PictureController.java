package com.joji.taowu.admin.controller;


import com.joji.taowu.admin.service.PictureService;
import com.joji.taowu.common.entity.Picture;
import com.joji.taowu.common.param.PictureParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 后台管理商品图片服务管理控制器
 * */
@RestController
@RequestMapping("/admin/picture")
public class PictureController {

    @Resource
    private PictureService pictureService;

    @GetMapping("list")
    public Object list(PictureParam pictureParam){
        return pictureService.listPic(pictureParam);
    }

    @PostMapping("add")
    public Object add(Picture picture){
        return pictureService.addPic(picture);
    }

    @PostMapping("remove")
    public Object remove(Integer productId){
        return pictureService.removePic(productId);
    }
}
