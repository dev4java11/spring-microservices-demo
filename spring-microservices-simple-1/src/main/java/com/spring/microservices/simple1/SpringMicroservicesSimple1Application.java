package com.spring.microservices.simple1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.microservices.simple1.client.Simple2Client;
import com.spring.microservices.simple1.configuration.RibbonConfiguration;
import com.spring.microservices.simple1.service.Simple2Service;

//@RibbonClient(name = "simple2")
@EnableEurekaClient
@EnableFeignClients
@EnableHystrix
@EnableHystrixDashboard
@SpringBootApplication
public class SpringMicroservicesSimple1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringMicroservicesSimple1Application.class, args);
	}
	
	@Bean
	@LoadBalanced
	public RestTemplate template(){
		return new RestTemplate();
	}
	
	
	@RestController
	public class MessageController{
		
		private RestTemplate template;
		
		private LoadBalancerClient loadBalancerClient;
		
		private Simple2Client simple2Client;
		
		private Simple2Service simple2Service;
		
		@Autowired
		public void setTemplate(RestTemplate template) {
			this.template = template;
		}
		
		@Autowired
		public void setLoadBalancerClient(LoadBalancerClient loadBalancerClient) {
			this.loadBalancerClient = loadBalancerClient;
		}
		
		@Autowired
		public void setSimple2Client(Simple2Client simple2Client) {
			this.simple2Client = simple2Client;
		}
		
		@Autowired
		public void setSimple2Service(Simple2Service simple2Service) {
			this.simple2Service = simple2Service;
		}
		
		@GetMapping("/message")
		public ResponseEntity<String> getMessage(){
			return ResponseEntity.ok("I'm a message.");
		}
		
		@GetMapping("/message/{value}")
		public ResponseEntity<String> getMessage(@PathVariable(name = "value", required = true) String value){
			value = StringUtils.isEmpty(value) ? "I'm a message.":value;
			return ResponseEntity.ok(value);
		}
		
		
		@GetMapping("/message/simple2/{value}")
		public ResponseEntity<?> getMessageSimple2(@PathVariable(name = "value", required = true) String value){
			ServiceInstance instance = loadBalancerClient.choose("simple2");
			if(instance != null){
				System.out.println(instance.getUri());
				System.out.println(instance.getHost());
				System.out.println(instance.getPort());
				System.out.println(instance.getServiceId());
			}
			String url = "http://simple2/message/"+value;
			ResponseEntity<String> response = template.getForEntity(url, String.class);
			return response;
//			return ResponseEntity.ok(value);
		}
		
		
		@GetMapping("/message/simple2/feing/{value}")
		public ResponseEntity<?> getMessageBySimple2Client(@PathVariable(name = "value", required = true) String value){
			ResponseEntity<?> response = simple2Client.getMessage(value);
			return response;
		}
		
		
		@GetMapping("/message/simple2/service/{value}")
		public ResponseEntity<?> getMessageBySimple2Service(@PathVariable(name = "value", required = true) String value){
			ResponseEntity<?> response = simple2Service.getMessage(value);
			return response;
		}
	}
	
	@RestController
	public class ServiceInstanceController{
		
		private DiscoveryClient discoveryClient;
		
		@Autowired
		public void setDiscoveryClient(DiscoveryClient discoveryClient) {
			this.discoveryClient = discoveryClient;
		}
		
		@GetMapping("/service-instances/{applicationName}")
	    public ResponseEntity<?> serviceInstancesByApplicationName(@PathVariable String applicationName) {
	        List<ServiceInstance> list = discoveryClient.getInstances(applicationName);
	        if(list == null || list.isEmpty()){
	        	ResponseEntity<?> response = ResponseEntity.ok("Not found instances for "+applicationName);
	        	return response;
	        }
			return ResponseEntity.ok(list);
	    }
	}
}
