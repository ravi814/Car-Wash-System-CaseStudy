package com.ondemandcarwash.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document(collection="washpack")
public class WashPack {
	
	@Id
	private int id;
	private String washpackName;
	private int washpackCost;
	private String WashpackDes;

}
