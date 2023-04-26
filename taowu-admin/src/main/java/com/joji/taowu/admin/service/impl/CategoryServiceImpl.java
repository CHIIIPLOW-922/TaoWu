package com.joji.taowu.admin.service.impl;

import com.joji.taowu.admin.service.CategoryService;
import com.joji.taowu.common.client.CategoryClient;
import com.joji.taowu.common.entity.Category;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.param.PictureParam;
import com.joji.taowu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 后台管理商品分类业务实现层
 * */
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryClient categoryClient;

    @Override
    @Cacheable(value="list.category",key = "#pictureParam.currentPage+'-'+#pictureParam.pageSize")
    public Object listPage(PictureParam pictureParam) {
        R r = categoryClient.pageList(pictureParam);

        log.info("CategoryServiceImpl.listPage业务结束，结果:{}",r);
        return r;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value="list.category",allEntries = true),
                    @CacheEvict(value = "category",allEntries = true)
            }
    )
    public Object update(Category category) {
        R r = categoryClient.update(category);
        log.info("CategoryServiceImpl.update业务结束，结果:{}",r);

        return r;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value="list.category",allEntries = true),
                    @CacheEvict(value = "category",key = "#category.categoryId")
            }
    )
    public Object remove(Category category) {
        R r = categoryClient.remove(category);
        log.info("CategoryServiceImpl.remove业务结束，结果:{}",r);
        return r;
    }

    @Override
    @CacheEvict(value = "list.category",allEntries = true)
    public Object save(Category category) {
        //类别数据保存
        R r = categoryClient.save(category);

        log.info("CategoryServiceImpl.save业务结束，结果:{}",r);

        return r;
    }
}
