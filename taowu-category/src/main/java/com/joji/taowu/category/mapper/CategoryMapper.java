package com.joji.taowu.category.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joji.taowu.common.entity.Category;
import feign.Param;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 淘物商城商品分类Dao层
 * */
public interface CategoryMapper extends BaseMapper<Category> {


    List<Category> list(@Param("currentPage")Integer currentPage,@Param("pageSize")Integer pageSize);
}
