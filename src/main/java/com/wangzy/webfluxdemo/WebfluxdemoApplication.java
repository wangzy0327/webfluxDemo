package com.wangzy.webfluxdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication
@EnableReactiveMongoRepositories
public class WebfluxdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebfluxdemoApplication.class, args);
	}
}
