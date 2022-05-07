package com.ondemandcarwash.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="Ratings")
public class Ratings {
	@Id
	private int washerId;
	private String status;
	private int rating;
	private String review;

}
