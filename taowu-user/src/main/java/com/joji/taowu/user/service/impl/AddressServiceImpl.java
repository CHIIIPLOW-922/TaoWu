package com.joji.taowu.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.joji.taowu.common.entity.Address;
import com.joji.taowu.common.param.AddressParam;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.user.mapper.AddressMapper;
import com.joji.taowu.user.service.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 商城用户地址业务实现层
 */
@Service
@Slf4j
public class AddressServiceImpl implements AddressService {


    @Resource
    AddressMapper addressMapper;


    /**
     * 地址查询服务
     * */
    @Override
    public R list(Integer userId) {
        //用户校验
        if (userId == null) {
            log.info("淘物商城用户地址业务开始，结果:{}", userId);
            return R.fail("地址查询失败");
        }
        //查询
        QueryWrapper<Address> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        List<Address> addressList = addressMapper.selectList(queryWrapper);
        log.info("淘物商城用户地址业务结束，结果:{}", addressList);
        return R.ok(addressList);
    }

    /**
     * 地址保存服务
     * */
    @Override
    public R save(AddressParam addressParam) {
        Address address = addressParam.getAddress();
        address.setUserId(addressParam.getUserId());

        //插入数据库
        int rows = addressMapper.insert(address);

        //返回结果处理
        if (rows == 0) {
            return R.fail("地址保存失败!");
        }

        log.info("淘物商城用户地址业务结束，结果:{}", address);
        //调用查询,返回全部数据!
        return list(address.getUserId());
    }

    /**
     * 地址删除服务
     * */
    @Override
    public R remove(Integer id) {
        if (id == null) {
            log.info("AddressServiceImpl.remove业务开始，参数:{}", id);
            return R.fail("地址移除失败!");
        }

        int rows = addressMapper.deleteById(id);


        log.info("AddressServiceImpl.remove业务结束，移除结果:{}", rows);

        if (rows == 0) {

            return R.fail("地址移除失败!");
        }

        return R.ok("地址移除成功!");
    }
}
