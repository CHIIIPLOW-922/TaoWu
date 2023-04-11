package com.joji.taowu.user.controller;


import com.joji.taowu.common.entity.User;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;

    /**
     * 用户注册
     * */
    @PostMapping("register")
    public R register(@RequestBody User user,@RequestParam String verifyCode){

        return userService.register(user,verifyCode);
    }

    /**
     * 短信发送
     * */
    @PostMapping("sendPhoneSms")
    public R sendPhoneSms(@RequestParam String phoneNumber){
        return userService.sendPhoneSms(phoneNumber);
    }

}
