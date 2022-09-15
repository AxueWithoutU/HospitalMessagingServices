package com.capstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.controller.MSServiceController;
import com.netflix.discovery.EurekaClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@SpringBootApplication
@ComponentScan({"com.capstone","com.capstone.controller","com.capstone.service","com.capstone.repository", "com.capstone.emailsendr", "com.capstone.model"})
@EntityScan({"com.capstone","com.capstone.controller","com.capstone.service","com.capstone.repository", "com.capstone.emailsendr", "com.capstone.model"})
@EnableScheduling
@RestController
@EnableAdminServer
@EnableDiscoveryClient
@EnableHystrix
public class CapstoneDbApplication implements MSServiceController {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneDbApplication.class, args);
	}
	
	@Autowired
	@Lazy
	private EurekaClient client;
	
	@Value("${spring.application.name}")
	private String appName;
	
	@Override 
	public String greeting() {
		return String.format("hello from '%s'!", 
				client.getApplication(appName).getName());
	}
}

