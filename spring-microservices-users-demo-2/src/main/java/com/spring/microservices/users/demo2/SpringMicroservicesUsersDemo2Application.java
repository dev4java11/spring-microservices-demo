package com.spring.microservices.users.demo2;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.spring.microservices.users.demo2.model.User;
import com.spring.microservices.users.demo2.repository.UserRepository;

import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringMicroservicesUsersDemo2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroservicesUsersDemo2Application.class, args);
	}
	
	@Bean
	public ApplicationRunner demoData(UserRepository repository){
		User u1 = new User();
		u1.setUsername("12345678");
		u1.setFullName("Nilo Hernán Rosas Nutz");
		u1.setEmail("hernan@gmail.com");
		u1.setPhone("997540007");
		u1.setDirection("Av. Belaunde Este 412 - Comas - Lima - Lima");
		
		User u2 = new User();
		u2.setUsername("12345679");
		u2.setFullName("Mary Sol Rosas Nutz");
		u2.setEmail("mary@gmail.com");
		u2.setPhone("997540008");
		u2.setDirection("Av. Belaunde Este 412 - Comas - Lima - Lima");
		
		
		User u3 = new User();
		u3.setUsername("12345680");
		u3.setFullName("Jessica Paola Rosas Nutz");
		u3.setEmail("paola@gmail.com");
		u3.setPhone("997540009");
		u3.setDirection("Av. Belaunde Este 412 - Comas - Lima - Lima");
		
		
		return args ->{
			repository
			.deleteAll().thenMany(
					Flux.just(u1, u2, u3)
					.map(User::new)
					.flatMap(repository::save))
			.thenMany(repository.findAll())
			.subscribe(System.out::println);
		};
	}
}
