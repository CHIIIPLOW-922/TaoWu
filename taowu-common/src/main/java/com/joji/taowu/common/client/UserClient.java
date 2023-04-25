package com.joji.taowu.common.client;


import com.joji.taowu.common.entity.User;
import com.joji.taowu.common.param.PageParam;
import com.joji.taowu.common.utils.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 商城用户服务Feign标准接口
 * */
@FeignClient(value = "taowu-user")
public interface UserClient {

    /**
     * 后台管理,展示用户信息接口
     * @param pageParam
     * @return
     */
    @PostMapping("/user/list")
    R listPage(@RequestBody PageParam pageParam);

    @PostMapping("/user/remove")
    R remove(@RequestBody User user);

    @PostMapping("/user/update")
    R update(@RequestBody User user);

    @PostMapping("/user/register")
    R save(@RequestBody User user);
}
