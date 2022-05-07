package com.ondemandcarwash.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document(collection="customer")
public class Customer {
	
	@Id
	private int id;
	private String name;
	private String phone;
	private String email;
	private String address;
	private String password;

}
