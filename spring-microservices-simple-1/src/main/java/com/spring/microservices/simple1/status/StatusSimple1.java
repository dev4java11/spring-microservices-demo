package com.spring.microservices.simple1.status;

import org.springframework.stereotype.Component;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo.InstanceStatus;

//@Component
public class StatusSimple1 implements HealthCheckHandler {

	private int counter = -1;
	
	@Override
	public InstanceStatus getStatus(InstanceStatus currentStatus) {
		InstanceStatus status = null;
		counter++;
		
		switch(counter){
			case 0  : status = InstanceStatus.OUT_OF_SERVICE;break;
			case 1  : status = InstanceStatus.DOWN;break;
			case 2  : status = InstanceStatus.STARTING;break;
			case 3  : status = InstanceStatus.UNKNOWN;break;
			default : status = InstanceStatus.UP;break;
		}
		
		if(counter == 10){
			counter = -1;
		}
		
		return status;
	}

}
