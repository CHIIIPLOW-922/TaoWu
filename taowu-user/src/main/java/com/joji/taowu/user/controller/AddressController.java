package com.joji.taowu.user.controller;


import com.joji.taowu.common.param.AddressParam;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.user.service.AddressService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 商城地址服务控制器层
 */
@RestController
@RequestMapping("/user/address")
public class AddressController {
    @Resource
    AddressService addressService;

    /**
     *  地址查询接口
     * */
    @PostMapping("list")
    public R list(@RequestBody Map<String, Integer> params){
        Integer userId = params.get("user_id");
        return addressService.list(userId);
    }

    /**
     * 地址保存接口
     * */
    @PostMapping("save")
    public R save(@RequestBody AddressParam addressParam){
        return addressService.save(addressParam);
    }

    /**
     * 地址删除接口
     * */
    @PostMapping("remove")
    public R remove(@RequestBody Map<String, Integer> params){
        Integer addressId = params.get("id");
        return addressService.remove(addressId);
    }
}
