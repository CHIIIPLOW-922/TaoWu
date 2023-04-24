package com.joji.taowu.product.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joji.taowu.common.entity.Picture;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 商品图片Dao层
 * */
public interface PictureMapper extends BaseMapper<Picture> {


    List<Picture> list(@Param("offset") Integer offset, @Param("number")Integer number);
}
