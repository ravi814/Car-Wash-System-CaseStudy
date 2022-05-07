package com.ondemandcarwash.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString

@Document(collection="admins")
public class Admin {
	@Id
	private int adminId;
	private String adminName;
	private String aEmail;
	private String aPassword;

}
