package com.joji.taowu.user.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joji.taowu.common.constants.SaltConstant;
import com.joji.taowu.common.entity.User;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.PictureParam;
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

/**
 * 商城用户业务实现层
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private SendSms sendSms;

    @Resource
    UserMapper userMapper;

    @Autowired
    RedisSmsCache redisSmsCache;




    /**
     * 检查账号是否可用
     *
     * @param userName
     * @return R
     */
    @Override
    public R check(String userName) {
        //1.账号非空校验
        if (StringUtils.isEmpty(userName)) {
            log.info("淘物商城账号检查业务开始，参数:{}", userName);
            return R.fail("账号不能为空，请重试!");
        }
        //2.数据库查询
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("user_name", userName);
        Long count = userMapper.selectCount(queryWrapper);

        if (count > 0) {
            return R.fail("账号已经存在,不可用!");
        }
        log.info("淘物商城账号检查业务结束，结果:{}", count);
        return R.ok("账号不存在,可以使用!");
    }


    /**
     * 用户注册业务
     */
    @Override
    public R register(User user, String verifyCode) {
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
            log.info("淘物商城用户注册业务结束，结果:{}", cachedVerificationCode);
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

        return R.fail("注册失败，请重新注册");
    }

    /**
     * 发送短信服务
     */
    @Override
    public R sendPhoneSms(String phoneNumber) {
        try {
            SendSmsResponse response = sendSms.sendSms(phoneNumber);
            log.info("短信发送成功:{}", response.getMessage());
        } catch (ClientException e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
        return R.ok("短信发送成功");
    }

    /**
     * 用户登录业务
     */
    @Override
    public R login(User user) {
        //登录账号信息填写是否完整验证
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getUserPassword())) {
            log.info("淘物商城用户登录业务结束，结果:{}", user);
            return R.fail("账号或者密码为空,注册失败!");
        }
        //密码加盐
        String saltPassword = MD5Util.encode(user.getUserPassword() + SaltConstant.PASSWORD_SALT);
        //加盐密码设置
        user.setUserPassword(saltPassword);

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", user.getUserName());
        queryWrapper.eq("user_password", user.getUserPassword());
        User loginResult = userMapper.selectOne(queryWrapper);

        if (loginResult == null) {
            return R.fail("账号或密码错误，登录失败!");
        }
        //防止将加盐处理后密码返回给前端
        loginResult.setUserPassword(null);
        log.info("淘物商城用户登录业务结束，业务成功：{}", loginResult);
        return R.ok("用户登录成功！", loginResult);
    }

    /**
     * 分页查询用户服务
     * */
    @Override
    public Object listPage(PictureParam pictureParam) {

        int currentPage = pictureParam.getCurrentPage()-1;
        int pageSize = pictureParam.getPageSize();


        //结果封装
        long total = userMapper.selectCount(null);
        List<User> records = userMapper.list((currentPage*pageSize),pageSize);


        R ok = R.ok("查询成功!", records, total);

        log.info("淘物商城分页查询业务结束，结果:{}",ok);

        return ok;
    }


    /**
     * 用户删除业务
     */
    @Override
    public Object remove(User user) {
        Integer userId = user.getUserId();
        int rows = userMapper.deleteById(userId);

        log.info("UserServiceImpl.remove业务结束，结果:{}", rows);

        if (rows > 0) {
            return R.ok("删除用户数据成功!");
        }
        return R.fail("删除用户数据失败!");
    }

    @Override
    public Object update(User user,String verifyCode) {
        //检查密码,如果和数据库一致 不需要加密! 证明密码没有修改!
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getUserId());
        queryWrapper.eq("user_password", user.getUserPassword());

        Long total = userMapper.selectCount(queryWrapper);

        //手机号码短信验证
        if (StringUtils.isEmpty(user.getUserPhone())) {
            log.info("淘物商城用户修改业务结束，结果:{}", user);
            return R.fail("用户修改业务手机号码为空，请补充");
        }
        String phoneNumber = user.getUserPhone();
        // 从缓存中获取该手机号码对应的验证码
        String cachedVerificationCode = redisSmsCache.get(phoneNumber);
        if (cachedVerificationCode == null || !cachedVerificationCode.equals(verifyCode)) {
            log.info("淘物商城用户修改业务结束，结果:{}", cachedVerificationCode);
            return R.fail("短信时效超时或验证码不正确，请重新验证");
        }

        if (total == 0) {
            //密码不同,已经修改! 新密码需要加密
            user.setUserPassword(MD5Util.encode(user.getUserPassword() + SaltConstant.PASSWORD_SALT));
        }

        int rows = userMapper.updateById(user);

        if (rows == 0) {
            return R.fail("用户修改失败!");
        }
        return R.ok("用户修改成功");
    }

    @Override
    public Object addUser(User user) {
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

        return R.fail("注册失败，请重新注册");
    }

    @Override
    public Object editUser(User user) {
        //检查密码,如果和数据库一致 不需要加密! 证明密码没有修改!
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", user.getUserId());
        queryWrapper.eq("user_password", user.getUserPassword());

        Long total = userMapper.selectCount(queryWrapper);
        //手机号码短信验证
        if (StringUtils.isEmpty(user.getUserPhone())) {
            log.info("淘物商城用户修改业务结束，结果:{}", user);
            return R.fail("用户修改业务手机号码为空，请补充");
        }
        if (total == 0) {
            //密码不同,已经修改! 新密码需要加密
            user.setUserPassword(MD5Util.encode(user.getUserPassword() + SaltConstant.PASSWORD_SALT));
        }
        int rows = userMapper.updateById(user);

        if (rows == 0) {
            return R.fail("用户修改失败!");
        }
        return R.ok("用户修改成功");
    }
}
