package com.joji.taowu.common.param;


import com.joji.taowu.common.entity.Product;
import lombok.Data;
/**
 * 商品图片参数类
 * */
@Data
public class ProductPictureParam extends Product {
    private String pictures;
}
