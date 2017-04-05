package com.jmansilla.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication(scanBasePackages = {
		"com.jmansilla.demo"
})
@EnableDiscoveryClient
public class Application {
	public static void main(String[] args){
		SpringApplication.run(Application.class, args);
	}
}
