package com.spring.microservices.users.demo1;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.stereotype.Component;

import com.spring.microservices.users.demo1.model.User;
import com.spring.microservices.users.demo1.service.UserService;

@EnableEurekaClient
@SpringBootApplication
public class SpringMicroservicesUsersDemo1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroservicesUsersDemo1Application.class, args);
	}
	
	@Component
	public class InitUsers implements CommandLineRunner{
		
		private UserService userService;
		
		@Autowired
		public void setUserService(UserService userService) {
			this.userService = userService;
		}

		@Override
		public void run(String... arg0) throws Exception {
			User u1 = new User();
			u1.setId("72617278");
			u1.setUuid(UUID.randomUUID().toString());
			u1.setUsername("72617278");
			u1.setFullName("Nilo Hernán Rosas Nutz");
			u1.setEmail("hernan@gmail.com");
			u1.setIdentityCard("72617278");
			u1.setPhone("997540007");
			u1.setDirection("Av. Belaunde Este 412 - Comas - Lima - Lima");
			
			User u2 = new User();
			u2.setId("72617279");
			u2.setUuid(UUID.randomUUID().toString());
			u2.setUsername("72617279");
			u2.setFullName("Mary Sol Rosas Nutz");
			u2.setEmail("mary@gmail.com");
			u2.setIdentityCard("72617279");
			u2.setPhone("997540008");
			u2.setDirection("Av. Belaunde Este 412 - Comas - Lima - Lima");
			
			
			User u3 = new User();
			u3.setId("72617280");
			u3.setUuid(UUID.randomUUID().toString());
			u3.setUsername("72617280");
			u3.setFullName("Jessica Paola Rosas Nutz");
			u3.setEmail("paola@gmail.com");
			u3.setIdentityCard("72617280");
			u3.setPhone("997540009");
			u3.setDirection("Av. Belaunde Este 412 - Comas - Lima - Lima");
			
			List<User> list = new ArrayList<>();
			list.add(u1);
			list.add(u2);
			list.add(u3);
			
			userService.setUsers(list);
		}
		
	} 
}