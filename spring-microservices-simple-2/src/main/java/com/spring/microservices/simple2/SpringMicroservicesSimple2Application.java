package com.spring.microservices.simple2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@EnableEurekaClient
@SpringBootApplication
public class SpringMicroservicesSimple2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroservicesSimple2Application.class, args);
	}
	
	
	@RestController
	public class Simple2MessageController{
		
		@GetMapping("/message")
		public ResponseEntity<String> getMessage(){
			return ResponseEntity.ok("I'm a message for simple2.");
		}
		
		@GetMapping("/message/{value}")
		public ResponseEntity<String> getMessage(@PathVariable(name = "value", required = true) String value){
			value = StringUtils.isEmpty(value) ? "I'm a message for simple2.": value+" for simple2.";
			return ResponseEntity.ok(value);
		}
	}
}
