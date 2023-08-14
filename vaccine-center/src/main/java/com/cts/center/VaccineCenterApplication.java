package com.cts.center;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author rahul
 * This is the Main Application
 */
@SpringBootApplication
@EnableFeignClients
@EnableSwagger2
public class VaccineCenterApplication {
	/**
	 * Main Function
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(VaccineCenterApplication.class, args);
	}

}
