package com.joji.taowu.admin.service;

import com.joji.taowu.common.entity.Picture;
import com.joji.taowu.common.param.PictureParam;

public interface PictureService {


    Object listPic(PictureParam pictureParam);

    Object addPic(Picture picture);

    Object removePic(Picture picture);
}
