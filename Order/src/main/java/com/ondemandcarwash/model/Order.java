package com.ondemandcarwash.model;

import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="orders")
public class Order {
	
	@Id
	@NotNull(message = "OrderId must be filled." )
	private Long orderId;
	@NotNull(message= "Menction car name.")
	private String carName;
	private String carModel;
	private String wName;
	private String date;
	private long phoneNo;
	private int cost;

}
