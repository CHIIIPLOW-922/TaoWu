package com.joji.taowu.order;

import com.joji.taowu.common.client.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan(basePackages = "com.joji.taowu.order.mapper")
@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
public class TaowuOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaowuOrderApplication.class, args);
	}

}
