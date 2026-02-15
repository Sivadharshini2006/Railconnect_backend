package com.railconnect.trainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
@ComponentScan(basePackages = "com.railconnect.trainservice")
public class TrainserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainserviceApplication.class, args);
	}

}
