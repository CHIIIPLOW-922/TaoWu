package com.joji.taowu.search;

import com.joji.taowu.common.client.ProductClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@EnableFeignClients(clients = ProductClient.class)
public class TaowuSearchApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaowuSearchApplication.class, args);
	}

}
