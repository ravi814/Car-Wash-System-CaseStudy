package com.ondemandcarwash.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="orders")
public class Order {
	@Id
	private int orderId;
	private String carName;
	private String carModel;
	private String wName;
	private String date;
	private long phoneNo;

}
