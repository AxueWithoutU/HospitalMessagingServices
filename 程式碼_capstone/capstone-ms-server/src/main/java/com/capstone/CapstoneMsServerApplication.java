package com.capstone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import de.codecentric.boot.admin.server.config.EnableAdminServer;


@SpringBootApplication
@EnableEurekaServer
@EnableAdminServer
public class CapstoneMsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CapstoneMsServerApplication.class, args);
	}

}
