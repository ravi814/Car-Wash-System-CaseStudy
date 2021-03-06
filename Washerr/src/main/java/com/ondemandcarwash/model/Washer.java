package com.ondemandcarwash.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Document(collection="washers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Washer {
	private int id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String password;

}
