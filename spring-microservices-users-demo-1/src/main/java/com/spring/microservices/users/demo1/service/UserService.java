package com.spring.microservices.users.demo1.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import org.springframework.stereotype.Service;

import com.spring.microservices.users.demo1.model.User;

@Service
public class UserService {

	private List<User> users;
	
	public User findByUUID(String uuid){
		User user = users.stream().filter(u -> u.getUuid().equalsIgnoreCase(uuid)).findFirst().orElse(null);
		return user;
	}
	
	public int getPositionByUUID(String uuid){
		int position = IntStream
		.range(0, users.size())
		.filter(i -> users.get(i).getUuid().equalsIgnoreCase(uuid))
		.findFirst()
		.orElse(-1);
		return position;
	}
	
	public User create(User user){
		user.setId(user.getIdentityCard());
		user.setUsername(user.getIdentityCard());
		user.setUuid(UUID.randomUUID().toString());
		users.add(user);
		return user;
	}
	
	public User update(User user){
		int position = getPositionByUUID(user.getUuid());
		if(position >= 0){
			users.set(position, user);
		}
		return user;
	}
	
	public void delete(String uuid){
		users.removeIf(u -> u.getUuid().equalsIgnoreCase(uuid));
	}
	
	public List<User> getUsers() {
		return users;
	}
	
	public void setUsers(List<User> users) {
		this.users = users;
	}
}
