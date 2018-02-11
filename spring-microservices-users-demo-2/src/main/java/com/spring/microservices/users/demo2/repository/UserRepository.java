package com.spring.microservices.users.demo2.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.spring.microservices.users.demo2.model.User;

public interface UserRepository extends ReactiveMongoRepository<User, String> {

	
}
