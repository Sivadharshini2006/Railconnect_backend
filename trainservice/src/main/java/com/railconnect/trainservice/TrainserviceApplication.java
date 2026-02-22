package com.railconnect.trainservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.hibernate.autoconfigure.HibernateJpaAutoConfiguration;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = {
	    DataSourceAutoConfiguration.class, 
	    HibernateJpaAutoConfiguration.class
	})
@ComponentScan(basePackages = "com.railconnect.trainservice")
public class TrainserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrainserviceApplication.class, args);
	}

}
