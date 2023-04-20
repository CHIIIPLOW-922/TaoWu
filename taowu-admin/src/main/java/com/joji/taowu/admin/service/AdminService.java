package com.joji.taowu.admin.service;


import com.joji.taowu.admin.param.AdminParam;
import com.joji.taowu.common.utils.R;

/**
 * 管理员业务层
 * */
public interface AdminService {
    /**
     * 后台管理登录页面
     * @param adminParam
     * @return
     */
    R login(AdminParam adminParam);
}
