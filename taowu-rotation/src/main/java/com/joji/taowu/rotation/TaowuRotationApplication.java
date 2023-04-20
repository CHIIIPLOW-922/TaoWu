package com.joji.taowu.rotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@MapperScan(basePackages = "com.joji.taowu.rotation.mapper")
public class TaowuRotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaowuRotationApplication.class, args);
	}

}
