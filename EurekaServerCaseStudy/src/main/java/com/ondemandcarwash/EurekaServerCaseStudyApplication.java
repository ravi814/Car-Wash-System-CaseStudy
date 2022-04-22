package com.ondemandcarwash;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerCaseStudyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServerCaseStudyApplication.class, args);
	}

}
