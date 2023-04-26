package com.joji.taowu.admin.service;

import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.ProductSearchParam;

/**、
 * 后台管理订单业务层
 * */
public interface OrderService {

    /**
     * 分页查询订单数据
     * @param productSearchParam
     * @return
     */
    Object list(ProductSearchParam productSearchParam);
}
