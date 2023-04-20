package com.joji.taowu.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joji.taowu.admin.mapper.AdminMapper;
import com.joji.taowu.admin.param.AdminParam;
import com.joji.taowu.admin.service.AdminService;
import com.joji.taowu.common.constants.SaltConstant;
import com.joji.taowu.common.entity.Admin;
import com.joji.taowu.common.utils.MD5Util;
import com.joji.taowu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 管理员业务实现层
 * */
@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Resource
    AdminMapper adminMapper;


    @Override
    public R login(AdminParam adminParam) {
        //密码加密处理
        //代码加密处理,注意加盐,生成常量
        String newPwd = MD5Util.encode(adminParam.getAdminPassword() +
                SaltConstant.PASSWORD_SALT);

        //数据库登录查询
        QueryWrapper<Admin> adminQueryWrapper =
                new QueryWrapper<>();

        adminQueryWrapper.eq("admin_account",adminParam.getAdminAccount());
        adminQueryWrapper.eq("admin_password",newPwd);

        Admin admin = adminMapper.selectOne(adminQueryWrapper);
        //结果封装

        if (admin == null) {
            return R.fail("账号或者密码错误!");
        }

        R ok = R.ok("用户登录成功!", admin);
        log.info("AdminServiceImpl.login业务结束，结果:{}",ok);

        return ok;
    }
}
