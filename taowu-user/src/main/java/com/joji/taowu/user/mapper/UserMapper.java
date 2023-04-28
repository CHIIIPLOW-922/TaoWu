package com.joji.taowu.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.joji.taowu.common.entity.Picture;
import com.joji.taowu.common.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 用户Dao层
 * */
public interface UserMapper extends BaseMapper<User> {



    List<User> list(@Param("offset") Integer offset, @Param("number")Integer number);
}
