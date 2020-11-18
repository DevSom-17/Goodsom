package com.example.goodsom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GoodsomApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(GoodsomApplication.class);
	} // validation.properties 파일을 위해 추가
	
	public static void main(String[] args) {
		SpringApplication.run(GoodsomApplication.class, args);
		System.out.println("Web Server START!");
	}

}
