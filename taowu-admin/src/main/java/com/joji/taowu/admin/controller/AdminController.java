package com.joji.taowu.admin.controller;


import com.joji.taowu.admin.param.AdminParam;
import com.joji.taowu.admin.service.AdminService;
import com.joji.taowu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;

@Slf4j
@RequestMapping("admin")
@RestController
public class AdminController {

    @Resource
    AdminService adminService;

    /**
     * 后台登录功能实现
     * @param adminParam
     * @return
     */
    /**
     * 用户登录
     */
    @PostMapping("login")
    public R login(@RequestBody AdminParam adminParam) {
        return adminService.login(adminParam);
    }
}
