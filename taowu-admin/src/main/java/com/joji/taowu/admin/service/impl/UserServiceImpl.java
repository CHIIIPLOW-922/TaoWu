package com.joji.taowu.admin.service.impl;

import com.joji.taowu.admin.service.UserService;
import com.joji.taowu.common.client.UserClient;
import com.joji.taowu.common.entity.User;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * 后台管理商城用户服务业务实现层
 * */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserClient userClient;

    @Override
    @Cacheable(value = "list.user",key = "#pageParam.currentPage+'-'+#pageParam.pageSize")
    public Object listPage(PageParam pageParam) {
        R r = userClient.listPage(pageParam);

        log.info("UserServiceImpl.listPage业务结束，结果:{}",r);

        return r;
    }

    @Override
    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true),
                    @CacheEvict(value = "user",key = "#user.userId" )
            }
    )
    public Object remove(User user) {
        R r = userClient.remove(user);

        log.info("UserServiceImpl.remove业务结束，结果:{}",r);
        return r;
    }

    @Override
    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true),
                    @CacheEvict(value = "user",key = "#user.userId" )
            }
    )
    public Object update(User user) {
        R r = userClient.update(user);
        log.info("UserServiceImpl.update业务结束，结果:{}",r);
        return r;
    }

    @Override
    @Caching(
            //删除,清空缓存集合
            //删除,清空对应单条id的数据
            evict = {
                    @CacheEvict(value = "list.user",allEntries = true)
            }
    )
    public Object save(User user) {
        R ok = userClient.save(user);
        log.info("UserServiceImpl.save业务结束，结果:{}",ok);
        return ok;
    }
}
