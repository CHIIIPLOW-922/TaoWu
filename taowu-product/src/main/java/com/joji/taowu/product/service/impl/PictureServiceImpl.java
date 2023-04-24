package com.joji.taowu.product.service.impl;


import com.joji.taowu.common.entity.Picture;
import com.joji.taowu.common.param.PictureParam;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.product.mapper.PictureMapper;
import com.joji.taowu.product.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class PictureServiceImpl implements PictureService {
    @Resource
    private PictureMapper pictureMapper;

    @Override
    public R list(PictureParam pictureParam) {
        List<Picture> pictureList = pictureMapper.list((pictureParam.getCurrentPage()-1)*pictureParam.getPageSize(), pictureParam.getPageSize());
        long total = pictureMapper.selectCount(null);
        if (pictureList !=null ){
            return R.ok("详情页商品图片加载成功",pictureList,total);
        }
        return R.fail("加载失败");
    }

    @Override
    public R add(Picture picture) {
        if (picture.getProductId() == null){
            return R.fail("添加图片必须选择指定商品id");
        }
        int row =  pictureMapper.insert(picture);
        if (row >0){
            return R.ok("图片添加成功！",row);
        }
        return R.fail("图片添加失败！");
    }

    @Override
    public R remove(Integer pictureId) {
        int row = pictureMapper.deleteById(pictureId);
        if (row > 0){
            return R.ok("商品图片删除成功！",row);
        }
        return R.fail("商品图片删除失败！");
    }


}
