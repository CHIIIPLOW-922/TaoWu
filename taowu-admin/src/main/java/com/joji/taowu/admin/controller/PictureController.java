package com.joji.taowu.admin.controller;


import com.joji.taowu.admin.service.PictureService;
import com.joji.taowu.common.entity.Picture;
import com.joji.taowu.common.param.PictureParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 后台管理商品图片服务管理控制器
 * */
@RestController
@RequestMapping("/admin/picture")
public class PictureController {

    @Resource
    private PictureService pictureService;

    @PostMapping("list")
    public Object list(@RequestBody PictureParam pictureParam){
        return pictureService.listPic(pictureParam);
    }

    @PostMapping("add")
    public Object add(@RequestBody Picture picture){
        return pictureService.addPic(picture);
    }

    @PostMapping("remove")
    public Object remove(@RequestBody Picture picture){
        return pictureService.removePic(picture);
    }
}
