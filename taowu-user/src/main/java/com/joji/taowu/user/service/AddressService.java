package com.joji.taowu.user.service;

import com.joji.taowu.common.param.AddressParam;
import com.joji.taowu.common.utils.R;

/**
 * 商城用户地址业务层
 * */
public interface AddressService {
    /**
     * 查询地址列表
     * @param userId
     * @return
     */
    R list(Integer userId);

    /**
     * 保存数据库数据
     * @param addressParam
     * @return
     */
    R save(AddressParam addressParam);

    /**
     * 删除地址数据
     * @param id
     * @return
     */
    R remove(Integer id);
}
