package com.joji.taowu.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 淘物商城后台管理员实体类
 * */
@Data
@TableName("admin")
public class Admin implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer adminId;
    private String adminName;
    private String adminAccount;
    private String adminPassword;
    private String adminPhone;
    private Date createTime;
    private Integer adminRole;
}
