package com.joji.taowu.admin.service.impl;

import com.joji.taowu.admin.service.PictureService;
import com.joji.taowu.common.client.ProductClient;
import com.joji.taowu.common.entity.Picture;
import com.joji.taowu.common.param.PictureParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class PictureServiceImpl implements PictureService{

    @Resource
    private ProductClient productClient;


    @Override
    public Object listPic(PictureParam pictureParam) {
        return productClient.listPic(pictureParam);
    }

    @Override
    public Object addPic(Picture picture) {
        return productClient.addPic(picture);
    }

    @Override
    public Object removePic(Integer productId) {
        return productClient.removePic(productId);
    }
}
