package com.joji.taowu.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@MapperScan(basePackages = "com.joji.taowu.product.mapper")
@SpringBootApplication
public class TaowuProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaowuProductApplication.class, args);
	}

}
