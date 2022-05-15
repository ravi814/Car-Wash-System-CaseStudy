package com.ondemandcarwash.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ondemandcarwash.model.Washer;

public interface WasherRepository extends MongoRepository<Washer, Integer> {
	
	Washer findByName(String name);
	Washer findByEmail(String email);

}
