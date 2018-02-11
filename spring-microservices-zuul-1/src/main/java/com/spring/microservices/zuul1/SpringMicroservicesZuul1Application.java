package com.spring.microservices.zuul1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class SpringMicroservicesZuul1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroservicesZuul1Application.class, args);
	}
	
	@Bean
	public CorsFilter corsFilter() {
	    final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    final CorsConfiguration config = new CorsConfiguration();
	    config.setAllowCredentials(true);
	    config.addAllowedOrigin("*");
	    config.addAllowedHeader("*");
	    config.addAllowedMethod(RequestMethod.OPTIONS.name());
	    config.addAllowedMethod(RequestMethod.HEAD.name());
	    config.addAllowedMethod(RequestMethod.GET.name());
	    config.addAllowedMethod(RequestMethod.PUT.name());
	    config.addAllowedMethod(RequestMethod.POST.name());
	    config.addAllowedMethod(RequestMethod.DELETE.name());
	    config.addAllowedMethod(RequestMethod.PATCH.name());
	    source.registerCorsConfiguration("/**", config);
	    return new CorsFilter(source);
	}
}
