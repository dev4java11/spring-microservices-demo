package com.spring.microservices.users.demo1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.microservices.users.demo1.model.User;
import com.spring.microservices.users.demo1.service.UserService;

@RestController
public class UserController {
	
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/")
	public ResponseEntity<List<User>> getAll(){
		List<User> users = userService.getUsers();
		if(users == null || users.isEmpty()){
			return ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok(users);
		}
	}
	
	@GetMapping("/{uuid}")
	public ResponseEntity<User> getByUUID(@PathVariable String uuid){
		User user = userService.findByUUID(uuid);
		if(user == null){
			return ResponseEntity.notFound().build();
		}else{
			return ResponseEntity.ok(user);
		}
	}
	
	
	@PostMapping("/")
	public ResponseEntity<User> create(@RequestBody User user){
		user = userService.create(user);
		return ResponseEntity.ok(user);
	}
	
	@PutMapping("/{uuid}")
	public ResponseEntity<User> update(@PathVariable String uuid, @RequestBody User user){
		user.setUuid(uuid);
		user = userService.update(user);
		return ResponseEntity.ok(user);
	}
	
	@DeleteMapping("/{uuid}")
	public ResponseEntity<String> delete(@PathVariable String uuid){
		userService.delete(uuid);
		return ResponseEntity.ok(uuid);
	}
}
