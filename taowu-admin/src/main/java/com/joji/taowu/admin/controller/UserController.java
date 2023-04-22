package com.joji.taowu.admin.controller;


import com.joji.taowu.admin.service.UserService;
import com.joji.taowu.common.entity.User;
import com.joji.taowu.common.param.PageParam;
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

    @GetMapping("list")
    @ResponseBody
    public Object list(PageParam pageParam){

        return userService.listPage(pageParam);
    }


    @PostMapping("remove")
    @ResponseBody
    public Object remove(Integer userId){

        if (userId == null){
            return R.fail("删除失败!");
        }
        return userService.remove(userId);
    }


    @PostMapping("update")
    @ResponseBody
    public Object update(User user){

        return userService.update(user);
    }


    @PostMapping("save")
    @ResponseBody
    public Object save(User user){

        return userService.save(user);
    }
}
