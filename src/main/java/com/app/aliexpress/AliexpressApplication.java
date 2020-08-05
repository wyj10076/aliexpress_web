package com.app.aliexpress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class AliexpressApplication {

	public static void main(String[] args) {
		SpringApplication.run(AliexpressApplication.class, args);
	}
	
}
