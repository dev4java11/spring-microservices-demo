package com.spring.microservices.eureka1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@EnableEurekaClient
@SpringBootApplication
public class SpringMicroservicesEureka1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroservicesEureka1Application.class, args);
	}
}
