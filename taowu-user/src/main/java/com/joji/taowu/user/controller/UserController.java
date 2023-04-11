package com.joji.taowu.user.controller;


import com.aliyuncs.exceptions.ClientException;
import com.joji.taowu.common.entity.User;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/**
 * 商城用户服务控制层
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Resource
    UserService userService;


    /**
     * 所有用户查询
     */
    @GetMapping("list")
    public List<User> list() {

        return userService.list();
    }

    /**
     * 重复用户验证
     */
    @PostMapping("check")
    public R check(@RequestBody User user) {
        return userService.check(user.getUserName());
    }


    /**
     * 用户注册
     */
    @PostMapping("register")
    public R register(@RequestBody User user, @RequestParam String verifyCode) {

        return userService.register(user, verifyCode);
    }

    /**
     * 短信发送
     */
    @PostMapping("sendPhoneSms")
    public R sendPhoneSms(@RequestParam String phoneNumber) {
        return userService.sendPhoneSms(phoneNumber);
    }

    /**
     * 用户登录
     */
    @PostMapping("login")
    public R login(@RequestBody User user) {
        return userService.login(user);
    }

    /**
     * 用户修改服务
     */
    @PostMapping("/update")
    public Object update(@RequestBody User user, @RequestParam String verifyCode) {
        return userService.update(user, verifyCode);
    }

    /**
     * 用户删除服务
     */
    @PostMapping("/remove")
    public Object remove(@RequestBody Integer userId) {
        return userService.remove(userId);
    }

    /**
     * 后台管理调用
     */
    @PostMapping("/list")
    public Object listPage(@RequestBody PageParam pageParam) {

        return userService.listPage(pageParam);
    }

}
