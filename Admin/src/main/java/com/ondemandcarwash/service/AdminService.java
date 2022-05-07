package com.ondemandcarwash.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ondemandcarwash.model.Admin;
import com.ondemandcarwash.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	
	    //method for adding admin
		public Admin addAdmin(Admin admin) 
		{
			return adminRepository.save(admin);
				
		}
		
		//Reading all admins
		public List<Admin> getAdmins() 
		{
			List<Admin> admins =adminRepository.findAll();
			System.out.println("Getting Admins from AdminDB " + admins);
			return admins;
		}
		
		//For deleting admin by id
		public void deleteById(int id) 
		{
			adminRepository.deleteById(id);
					
		}
		
		//For deleting admin 
		public void deleteAdmin(Admin admin)
		{
			adminRepository.delete(admin);
		}
		


}
