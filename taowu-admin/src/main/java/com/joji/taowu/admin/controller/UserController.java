package com.joji.taowu.admin.controller;


import com.joji.taowu.admin.service.UserService;
import com.joji.taowu.common.entity.User;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.PictureParam;
import com.joji.taowu.common.utils.R;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 后台管理用户管理控制器
 * */
@RestController
@RequestMapping("/admin/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("list")
    public Object list(@RequestBody PictureParam pictureParam){

        return userService.listPage(pictureParam);
    }


    @PostMapping("remove")
    public Object remove(@RequestBody User user){
        Integer userId = user.getUserId();
        if (userId == null){
            return R.fail("删除失败!");
        }
        return userService.remove(user);
    }


    @PostMapping("update")
    public Object update(@RequestBody User user){

        return userService.update(user);
    }


    @PostMapping("save")
    public Object save(@RequestBody User user){

        return userService.save(user);
    }
}
