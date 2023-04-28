package com.joji.taowu.common.param;


import lombok.Data;

import java.io.Serializable;

@Data
public class PictureParam implements Serializable {
    public static final Long serialVersionUID = 1L;

    private Integer currentPage=1;
    private Integer pageSize=15;
}
