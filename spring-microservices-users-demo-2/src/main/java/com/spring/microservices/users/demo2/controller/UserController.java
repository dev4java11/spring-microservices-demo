package com.spring.microservices.users.demo2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microservices.users.demo2.model.User;
import com.spring.microservices.users.demo2.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
	
	private UserRepository repository;
	
	@Autowired
	public void setRepository(UserRepository repository) {
		this.repository = repository;
	}

	@GetMapping(path = "/")
	public Flux<User> getAll(){
		return repository.findAll();
	}
	
	@GetMapping(path = "/{id}")
	public Mono<ResponseEntity<User>> get(@PathVariable String id){
		return repository
				.findById(id)
				.map(u -> ResponseEntity.ok(u))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping(path = "/")
	public Mono<ResponseEntity<?>> post(@RequestBody User user){
		user.setId(null);
		return repository
				.save(user)
				.then()
				.map(u -> ResponseEntity.ok(u));
	}
	
	@PutMapping(path = "/{id}")
	public Mono<ResponseEntity<User>> put(@PathVariable String id, @RequestBody User user){
		return repository
				.findById(id)
				.flatMap(u -> {
					u.setUsername(user.getUsername());
					u.setFullName(user.getFullName());
					u.setEmail(user.getEmail());
					u.setPhone(user.getPhone());
					u.setDirection(user.getDirection());
					return repository.save(u);
				})
				.map(uptated -> ResponseEntity.ok(uptated))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path = "/{id}")
	public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
		return repository.deleteById(id)
				.map(u -> ResponseEntity.ok(u))
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@CrossOrigin(origins = "*", allowedHeaders = "*", methods = RequestMethod.GET, allowCredentials = "false")
	@GetMapping(value = "/stream/", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<ResponseEntity<User>> streamAllTweets() {
        return repository
        		.findAll()
        		.map(u -> ResponseEntity.ok(u))
        		.defaultIfEmpty(ResponseEntity.noContent().build());
    }
}
