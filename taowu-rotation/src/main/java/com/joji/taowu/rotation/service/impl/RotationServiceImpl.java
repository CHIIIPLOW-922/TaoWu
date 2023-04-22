package com.joji.taowu.rotation.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.joji.taowu.common.entity.Rotation;
import com.joji.taowu.common.utils.R;
import com.joji.taowu.rotation.mapper.RotationMapper;
import com.joji.taowu.rotation.service.RotationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 轮播图服务业务实现层
 * */
@Slf4j
@Service
public class RotationServiceImpl implements RotationService {


    @Resource
    private RotationMapper rotationMapper;

    /**
     * 查询优先级最高的四条轮播图数据
     *
     * @return
     */
    @Cacheable(value = "list.rotation",key = "#root.methodName")
    @Override
    public Object list() {
        //声明数量
        int limit = 4 ; //至多查询四条
        //查询数据库
        IPage<Rotation> iPage = new Page<>(1,limit);
        QueryWrapper<Rotation> rotationQueryWrapper = new QueryWrapper<>();
        rotationQueryWrapper.orderByDesc("priority");
        IPage<Rotation> page = rotationMapper.selectPage(iPage, rotationQueryWrapper);

        List<Rotation> rotationList = page.getRecords();
        long total = page.getTotal();
        System.out.println("total = " + total);

        return R.ok(rotationList);
    }
}
