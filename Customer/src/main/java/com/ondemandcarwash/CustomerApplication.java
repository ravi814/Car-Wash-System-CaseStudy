package com.ondemandcarwash;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@SpringBootApplication
@EnableEurekaClient
public class CustomerApplication {
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate()
	{
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(CustomerApplication.class, args);
	}
	
	public static final String CUSTOMER_TAG = "Customer service";
	  
	 @Bean 
	 public Docket swaggerConfiguration() 
	 { 
		 return new Docket(DocumentationType.SWAGGER_2) 
				 .select()
				 .paths(PathSelectors.ant("/customer/**"))
				 .apis(RequestHandlerSelectors.basePackage("com.ondemandcarwash")) 
				 .build()
				 .tags(new Tag(CUSTOMER_TAG, "the customer service api with description api tag"));
	 }
	 
		

}
