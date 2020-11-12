package com.example.goodsom;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@SpringBootApplication
@EnableScheduling
public class GoodsomApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoodsomApplication.class, args);
		System.out.println("Web Server START!");
	}

}
