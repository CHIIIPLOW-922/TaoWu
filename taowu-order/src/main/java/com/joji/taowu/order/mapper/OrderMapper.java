package com.joji.taowu.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joji.taowu.common.entity.Order;
import com.joji.taowu.common.vo.AdminOrderVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 订单Dao层
 * */
public interface OrderMapper extends BaseMapper<Order> {

    /**
     * 分页查询数据,返回order封装vo
     * @param offset
     * @param number
     * @return
     */
    List<AdminOrderVO> selectAdminOrders(@Param("offset") int offset, @Param("number")int number);
}
