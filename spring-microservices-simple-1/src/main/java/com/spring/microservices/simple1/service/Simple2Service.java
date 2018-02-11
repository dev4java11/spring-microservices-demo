package com.spring.microservices.simple1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.microservices.simple1.client.Simple2Client;

@Service
public class Simple2Service {

	private Simple2Client simple2Client;
	
	@Autowired
	public void setSimple2Client(Simple2Client simple2Client) {
		this.simple2Client = simple2Client;
	}
	
	@HystrixCommand(fallbackMethod = "getDefaultMessage")
	public ResponseEntity<?> getMessage(String value){
		return simple2Client.getMessage(value);
	}
	
	public ResponseEntity<?> getDefaultMessage(String value){
		return ResponseEntity.ok("Get message for simple2");
	}
}
