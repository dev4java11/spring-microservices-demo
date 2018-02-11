package com.spring.microservices.users.demo2.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

	@Id
	private String id;
	private String username;
	private String fullName;
	private String email;
	private String phone;
	private String direction;
	
	public User(){
		
	}
	
	public User(User user){
		this.username = user.getUsername();
		this.fullName = user.getFullName();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.direction = user.getDirection();
	}
	
	public User(String username, String fullName, String email, String phone, String direction) {
		super();
		this.username = username;
		this.fullName = fullName;
		this.email = email;
		this.phone = phone;
		this.direction = direction;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", fullName=" + fullName + ", email=" + email
				+ ", phone=" + phone + ", direction=" + direction + "]";
	}
	
	
}
