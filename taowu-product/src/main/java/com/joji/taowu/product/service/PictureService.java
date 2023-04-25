package com.joji.taowu.product.service;


import com.joji.taowu.common.entity.Picture;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.PictureParam;
import com.joji.taowu.common.utils.R;

/**
 * 商品详情页轮播图业务层
 * */
public interface PictureService {

    R list(PictureParam pictureParam);


    R add(Picture picture);

    R remove(Picture picture);
}
