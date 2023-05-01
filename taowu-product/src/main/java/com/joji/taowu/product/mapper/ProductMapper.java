package com.joji.taowu.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joji.taowu.common.entity.Picture;
import com.joji.taowu.common.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 商品Dao层
 * */
public interface ProductMapper extends BaseMapper<Product> {

    List<Product> list(@Param("offset") Integer offset, @Param("number")Integer number);
}
