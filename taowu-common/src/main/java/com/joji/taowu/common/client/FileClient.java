package com.joji.taowu.common.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * 购物车Feign标准接口
 * */
@FeignClient(value = "taowu-static")
public interface FileClient {


    @PostMapping("/files/upload")
    String upload(@RequestParam("file") MultipartFile file);
}
