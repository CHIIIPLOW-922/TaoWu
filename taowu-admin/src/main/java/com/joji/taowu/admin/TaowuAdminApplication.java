package com.joji.taowu.admin;

import com.joji.taowu.common.client.CategoryClient;
import com.joji.taowu.common.client.OrderClient;
import com.joji.taowu.common.client.ProductClient;
import com.joji.taowu.common.client.UserClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(basePackages = "com.joji.taowu.admin.mapper")
@SpringBootApplication
@EnableCaching //开启缓存支持
@EnableFeignClients(clients = {UserClient.class, CategoryClient.class, ProductClient.class, OrderClient.class})  //添加客户端引用
public class TaowuAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaowuAdminApplication.class, args);
	}

}
