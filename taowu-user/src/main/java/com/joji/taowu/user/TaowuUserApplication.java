package com.joji.taowu.user;

import com.joji.taowu.user.utils.SendSms;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@ComponentScan("com.joji.taowu")
@MapperScan(basePackages = "com.joji.taowu.user.mapper")
@SpringBootApplication
public class TaowuUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaowuUserApplication.class, args);
	}

}
