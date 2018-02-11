package com.spring.microservices.simple1.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("simple2")
public interface Simple2Client {

	@GetMapping(path = "/message/{value}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	ResponseEntity<String> getMessage(@PathVariable(name = "value") String value);
}
