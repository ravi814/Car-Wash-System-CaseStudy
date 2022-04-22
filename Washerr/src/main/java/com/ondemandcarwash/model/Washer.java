package com.ondemandcarwash.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(collection="washers")
@Data
public class Washer {
	private int id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String password;

}
