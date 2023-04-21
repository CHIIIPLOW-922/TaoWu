package com.joji.taowu.cart;

import com.joji.taowu.common.client.ProductClient;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = {ProductClient.class})
@MapperScan(basePackages = "com.joji.taowu.cart.mapper")
public class TaowuCartApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaowuCartApplication.class, args);
	}

}
