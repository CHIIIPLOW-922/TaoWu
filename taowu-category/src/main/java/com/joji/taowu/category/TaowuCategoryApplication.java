package com.joji.taowu.category;

import com.joji.taowu.common.client.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@MapperScan(basePackages = "com.joji.taowu.category.mapper")
@EnableFeignClients(clients = {ProductClient.class})
public class TaowuCategoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaowuCategoryApplication.class, args);
	}

}
