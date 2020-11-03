package com.example.goodsom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
public class GoodsomApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsomApplication.class, args);
		System.out.println("Web Server START!");
	}
}
