package com.deyun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@MapperScan(basePackages ="com.deyun.dao")
@SpringBootApplication
@EnableCaching
public class SportplayerApplication {
	public static void main(String[] args) {
		SpringApplication.run(SportplayerApplication.class, args);
		System.out.println("###########################\n" +
				"### SPORT 项目启动成功 ####\n" +
				"###########################\n");
	}
}
