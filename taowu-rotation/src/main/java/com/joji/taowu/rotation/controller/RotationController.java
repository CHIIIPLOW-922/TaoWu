package com.joji.taowu.rotation.controller;


import com.joji.taowu.rotation.service.RotationService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 淘物商城轮播图服务Controller类
 * */
@RestController
@RequestMapping("rotation")
public class RotationController {

    @Resource
    RotationService rotationService;
    /**
     * 查询首页数据,查询优先级最高的四条
     * @return
     */
    @PostMapping("list")
    public Object list(){

        return  rotationService.list();
    }
}
