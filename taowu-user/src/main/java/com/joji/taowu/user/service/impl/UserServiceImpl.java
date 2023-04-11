package com.joji.taowu.user.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joji.taowu.common.constants.SaltConstant;
import com.joji.taowu.common.entity.User;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.utils.MD5Util;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.user.mapper.UserMapper;
import com.joji.taowu.user.service.UserService;
import com.joji.taowu.user.utils.RedisSmsCache;
import com.joji.taowu.user.utils.SendSms;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SendSms sendSms;

    @Resource
    UserMapper userMapper;

    @Autowired
    RedisSmsCache redisSmsCache;


    @Override
    public List<User> list() {
        return null;
    }

    @Override
    public R check(String userName) {
        return null;
    }


    @Override
    public R register(User user,String verifyCode) {
        //注册信息是否完整校验
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getUserPassword())) {
            log.info("淘物商城用户注册业务结束，结果:{}", user);
            return R.fail("账号或者密码为空,注册失败!");
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        Long result = userMapper.selectCount(queryWrapper);
        //账号是否重复校验
        if (result > 0) {
            log.info("淘物商城用户注册业务结束，结果:{}", result);
            return R.fail("该账号已存在，请重新注册");
        }

        //手机号码短信验证
        if (StringUtils.isEmpty(user.getUserPhone())) {
            log.info("淘物商城用户注册业务结束，结果:{}", user);
            return R.fail("注册手机号码为空，请补充");
        }
        String phoneNumber = user.getUserPhone();
        // 从缓存中获取该手机号码对应的验证码
        String cachedVerificationCode = redisSmsCache.get(phoneNumber);
        if (cachedVerificationCode == null || !cachedVerificationCode.equals(verifyCode)) {
            log.info("淘物商城用户注册业务结束，结果:{}",cachedVerificationCode);
            return R.fail("短信时效超时或验证码不正确，请重新验证");
        }
        //密码加盐
        String saltPassword = MD5Util.encode(user.getUserPassword() + SaltConstant.PASSWORD_SALT);
        //数据库保存加盐密码
        user.setUserPassword(saltPassword);
        //插入新用户数据
        int row = userMapper.insert(user);

        if (row > 0) {
            log.info("淘物商城用户注册业务结束，结果:{}", row);
            return R.ok("用户注册成功！");
        }

        return R.fail("该账号已存在，请重新注册");
    }

    @Override
    public R sendPhoneSms(String phoneNumber) {
        try{
            SendSmsResponse response = sendSms.sendSms(phoneNumber);
            log.info("短信发送成功:{}",response.getMessage());
        }catch (ClientException e){
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return R.ok("短信发送成功");
    }

    @Override
    public R login(User user) {
        return null;
    }

    @Override
    public Object listPage(PageParam pageParam) {
        return null;
    }

    @Override
    public Object remove(Integer userId) {
        return null;
    }

    @Override
    public Object update(User user) {
        return null;
    }
}
