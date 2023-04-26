package com.joji.taowu.admin.service;

import com.joji.taowu.common.entity.User;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.PictureParam;

/**
 * 后台管理系统商城用户业务层
 * */
public interface UserService {

    /**
     * 分页数据查询,用户模块
     * @param pictureParam
     * @return
     */
    Object listPage(PictureParam pictureParam);

    /**
     * 删除用户数据
     * @param user
     * @return
     */
    Object remove(User user);

    /**
     * 修改用户数据
     * @param user
     * @return
     */
    Object update(User user);

    /**
     * 保存用户数据,用户注册功能
     * @param user
     * @return
     */
    Object save(User user);
}
