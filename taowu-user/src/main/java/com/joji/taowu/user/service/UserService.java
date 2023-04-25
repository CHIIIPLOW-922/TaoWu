package com.joji.taowu.user.service;

import com.aliyuncs.exceptions.ClientException;
import com.joji.taowu.common.entity.User;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.utils.R;

import java.util.List;

/**
 * 商城用户业务层
 * */
public interface UserService {

    List<User> list();

    /**
     * 检查账号是否可用
     * @param userName
     * @return
     */
    R check(String userName);

    /**
     * 进行账号注册
     * @param user 参数没有校验
     * @param verifyCode
     * @return
     */
    R register(User user,String verifyCode);


    /**
     * 短信发送
     * @param phoneNumber
     * @return
     * */
    R sendPhoneSms(String phoneNumber);

    /**
     * 进行账号登录
     * @param user
     * @return
     */
    R login(User user);

    /**
     * 分页数据查询
     * @param pageParam
     * @return
     */
    Object listPage(PageParam pageParam);

    /**
     * 删除用户数据
     * @param user
     * @return
     */
    Object remove(User user);

    /**
     * 修改用户密码
     * @param user
     * @return
     */
    Object update(User user,String verifyCode);
}
