package com.joji.taowu.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan("com.joji.taowu")
@MapperScan(basePackages = "com.joji.taowu.user.mapper")
@SpringBootApplication
public class TaowuUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaowuUserApplication.class, args);
	}

}
