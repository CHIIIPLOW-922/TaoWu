package com.joji.taowu.common.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.joji.taowu.common.entity.Cart;
import com.joji.taowu.common.entity.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 购物车显示对象类
 * */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
public class CartVO implements Serializable {

    private Integer id;  //购物车id
    private Integer productID;  //商品id
    private String  productName; //商品名称
    private String  productImg; //商品显示图片
    private BigDecimal price;  //商城价格
    private Integer num;  //商品购买数量
    private Integer maxNum; //商品限购数量
    private Boolean check = false; //是否勾选

    public CartVO(Product product, Cart cart) {
        this.id = cart.getCartId();
        this.productID = product.getProductId();
        this.productName = product.getProductName();
        this.productImg = product.getProductPicture();
        this.price = product.getProductSellprice();
        this.num = cart.getCartNum();
        this.maxNum = product.getProductNum();
        this.check = false;
    }
}
