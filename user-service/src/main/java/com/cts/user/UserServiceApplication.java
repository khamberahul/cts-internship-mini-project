package com.cts.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author rahul
 * This is the Main Application
 */
@EnableSwagger2
@EnableFeignClients
@SpringBootApplication
public class UserServiceApplication {
	/**
	 * Main Function
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}
	

}
