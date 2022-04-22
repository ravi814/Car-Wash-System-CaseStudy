package com.ondemandcarwash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ondemandcarwash.model.Washer;
import com.ondemandcarwash.repository.WasherRepository;

@Service
public class WasherService {
	@Autowired
	private WasherRepository washerRepository;
	
	

	/*
	 * @Override public UserDetails loadUserByUsername(String username) throws
	 * UsernameNotFoundException {
	 * 
	 * return null;
	 * 
	 * }
	 */
	
	// for adding washer
	public Washer addWasher(Washer washer)
	{
		return washerRepository.save(washer);
	}
	
	// for getting all washers
	public List<Washer> getWashers() 
	{
		// TODO Auto-generated method stub
		List<Washer> washer= washerRepository.findAll();
		System.out.println("Getting Washer from DB" + washer);
		return washer;
	}
	
	// for deleting washer
	public void deleteWasher(Washer washer) 
	{
		washerRepository.delete(washer);
	}
	
	// deleting washer by id
	public void deleteById(int id)
	{
		washerRepository.deleteById(id);
		
	}

}
