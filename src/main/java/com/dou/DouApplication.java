package com.dou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@EnableScheduling // 开启定时任务
@MapperScan(value = "com.dou.dao")
public class DouApplication {

	public static void main(String[] args) {
		SpringApplication.run(DouApplication.class, args);
	}

}
