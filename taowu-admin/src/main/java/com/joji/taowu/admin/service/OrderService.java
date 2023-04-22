package com.joji.taowu.admin.service;

import com.joji.taowu.common.param.PageParam;
/**、
 * 后台管理订单业务层
 * */
public interface OrderService {

    /**
     * 分页查询订单数据
     * @param pageParam
     * @return
     */
    Object list(PageParam pageParam);
}
