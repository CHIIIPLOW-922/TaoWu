package com.joji.taowu.common.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


/**
 * 用户收货地址实体
* */
@Data
@TableName("address")
public class Address {

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String address;
    private String receiver;
    private String receiverPhone;
    @TableField("user_id")
    private Integer userId;

}