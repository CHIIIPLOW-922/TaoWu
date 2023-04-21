package com.joji.taowu.wishlist;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.joji.taowu.wishlist.mapper")
@SpringBootApplication
public class TaowuWishlistApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaowuWishlistApplication.class, args);
	}

}
