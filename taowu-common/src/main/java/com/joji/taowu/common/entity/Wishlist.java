package com.joji.taowu.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * 收藏夹实体类
 * */
@Data
public class Wishlist implements Serializable {
    public static final Long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Integer wishlistId;
    private Integer userId;
    private Integer productId;
    private Long    addTime;
}
