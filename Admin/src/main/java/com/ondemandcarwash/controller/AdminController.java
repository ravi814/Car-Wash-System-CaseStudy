package com.ondemandcarwash.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ondemandcarwash.model.Admin;
import com.ondemandcarwash.model.Customer;
import com.ondemandcarwash.model.Order;
import com.ondemandcarwash.model.Ratings;
import com.ondemandcarwash.model.WashPack;
import com.ondemandcarwash.model.Washer;
import com.ondemandcarwash.model.adminAuthResponse;
import com.ondemandcarwash.repository.AdminRepository;
import com.ondemandcarwash.repository.RatingRepository;
import com.ondemandcarwash.repository.WashPackRepository;
import com.ondemandcarwash.service.AdminService;
import com.ondemandcarwash.service.RatingService;


@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private AuthenticationManager  authenticationManager;
	
	@Autowired
	private WashPackRepository washPackRepository;
	
	@Autowired
	private RatingService ratingService;
	
	
	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
//	
	// Authenticating admin with email and password.
	// if details are correct it will display "Successfully Authenticated admin"
	// Else it will display "Error during  admin Authentication ".
	@PostMapping("/auth")
	private ResponseEntity<?> authAdmin(@RequestBody Admin admin){
		String email = admin.getaEmail();
		String password = admin.getaPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
				
		} catch (Exception e) {
				
			return ResponseEntity.ok(new adminAuthResponse("Error during  admin Authentication "+ email));
		}
		return ResponseEntity.ok(new adminAuthResponse("Successfully Authenticated admin "+ email));
			
	}
	
	
	
	
	    
	    // creating admin using addadmin post mapping through /admin/addadmin
		@PostMapping("/addadmin")
		public Admin saveAdmin(@RequestBody Admin admin) 
		{
			return adminService.addAdmin(admin);
				
		}
		
		// Welcome message through /admin/
		@GetMapping("/")
		public String message() 
		{
			return "Hello! WELCOME to Admin page";
		}
		

		//reading all admins using get request through /admin/alladmins
		@GetMapping("/alladmins")
		public List<Admin> findAllAdmins()
		{
			return adminService.getAdmins();
				
		}
		
		//reading admin by id using get request through /admin/alladmins/any id
		@GetMapping("/alladmins/{id}")
		public Optional<Admin> getAdminById(@PathVariable int id)
		{
			return adminRepository.findById(id);
		}
		
		
		//Updating Admin Data by Id with put request through /admin/update/any id
		@PutMapping("/update/{id}")
		public ResponseEntity<Object> updateAdmin(@PathVariable int id, @RequestBody Admin admin)
		{
			
			adminRepository.save(admin);
			return new ResponseEntity<Object>("Admin information updated successfully with id " +id, HttpStatus.OK);	
		}
		
		// Deleting Admin Data by Id through /admin/delete/any id
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Object> deleteAdmin (@PathVariable int id)
		{	
			adminService.deleteById(id);
			return new ResponseEntity<Object>("Admininfo deleted with id"+id,HttpStatus.OK);		
		}
		
		
// WashPack --------------------------		
		
		//adding washpack through /admin/addpack
		@PostMapping("/addpack")
		public String savePack(@RequestBody WashPack washpack)
		{
			washPackRepository.save(washpack);
			return " Pack saved successfully with id :"+washpack.getId();
		}
		
		
		//Reading all washpacks through /admin/allpacks
		@GetMapping("/allpacks")
		public List<WashPack> getAllPack()
		{
			return washPackRepository.findAll();
		}
		
		
		//Reading Washpack by ID through /admin/allpacks/any id
		@GetMapping("/allpacks/{id}")
		public Optional<WashPack> getPackById(@PathVariable int id)
		{
			return washPackRepository.findById(id);
		}
		
		
		//Updating WashPack by Id through /admin/packupdate/any id
		@PutMapping("/packupdate/{id}")
		public ResponseEntity<Object> updateWashPacks(@PathVariable int id, @RequestBody WashPack washpack)
		{	
			washPackRepository.save(washpack);
			return new ResponseEntity<Object>("WashPack updated successfully with id " +id, HttpStatus.OK);	
		}
		
		//Deleting washpack by id through /admin/deletepack/any id
		@DeleteMapping("/deletepack/{id}")
		public String deletePack(@PathVariable int id)
		{
			washPackRepository.deleteById(id);
			return "pack deleted with id "+id;
		}
		
		
// Ratings ---------------------
		
		//Adding rating through /admin/addrating
		@PostMapping("/addrating")
		public String saveRating(@RequestBody Ratings rating)
		{
			ratingService.addRating(rating);
			return " Thanks for Your Valuable feedback " + rating;
		}
		
		
		//Reading all ratings through /admin/allratings
		@GetMapping("/allratings")
		public List<Ratings> getAllRating()
		{
			return ratingService.getAllRating();
		}
		
		
		//Updating Ratings by Id through /admin/ratingupdate/any id
		@PutMapping("/ratingupdate/{id}")
		public ResponseEntity<Object> updateRating(@PathVariable int id, @RequestBody Ratings rating)
		{	
			ratingRepository.save(rating);
			return new ResponseEntity<Object>("Rating updated successfully with id " +id, HttpStatus.OK);	
		}
		
		
		//Read Rating By washerId through /admin/ratings/any id
		@GetMapping("/ratings/{id}")
		public Optional<Ratings> getRatingById(@PathVariable int id) 
		{
			return ratingRepository.findById(id);
		}
		
		
		
		// Deleting Rating by washerId through /admin/deleterating/any id
		@DeleteMapping("/deleterating/{id}")
		public ResponseEntity<Object> deleteRating(@PathVariable int id) 
		{
			ratingService.deleteRatingById(id);
			return new ResponseEntity<Object>("Deleted rating with id" + id, HttpStatus.OK);
		}
		
		
		
		
		
		
// Customer ------------------------------------------
		
	    // Getting all customers through /admin/allcustomers
		@GetMapping("/allcustomers")
		public List<Customer> findAllCustomers()
		{
			String baseurl="http://Customer-Service/customer/allcustomers";
			Customer[] allcustomers=restTemplate.getForObject(baseurl, Customer[].class);
			
			return Arrays.asList(allcustomers);
		}
		
		
		//Remove Customer By Id through /admin/removecustomer/any id
		@DeleteMapping("/removecustomer/{id}")
		public String removeCustomer(@PathVariable("id") int id) 
		{
			restTemplate.delete("http://Customer-Service/customer/delete/" +id , String.class);
			return "Customer is successfully Removed " + id;
		}
		
		
		
		
// Washer ------------------------
	    // reading all washers through /admin/allwashers
		@GetMapping("/allwashers")
		public List<Washer> findAllWashers()
		{
			String baseurl="http://Washer-Service/washer/allwashers";
			Washer[] allWashers=restTemplate.getForObject(baseurl, Washer[].class);
			
			return Arrays.asList(allWashers);
		}
		
		

		//Remove Washer By Id through /admin/removewasher/any id
		@DeleteMapping("/removewasher/{id}")
		public String removeWasher(@PathVariable("id") int id) 
		{
			restTemplate.delete("http://Washer-Service/washer/delete/" +id , String.class);
			return "Removed washer with " + id;
		}
		
		
		
		
// Orders-------------------------------------------------
		
		
	    // Reading all orders through /admin/allorders
		@GetMapping("/allorders")
		public String getAllOrder()
		{
			return restTemplate.getForObject("http://Order-Service/order/allorders", String.class);
		}
		
		//Reading orders By id through /admin/allorders/any id
		@GetMapping("/allorders/{id}")
		public Order getOrderById (@PathVariable("id") int id)
		{
			return restTemplate.getForObject("http://Order-Service/order/orders/"+id , Order.class);
		}
		
		//Cancel Order By Id through /admin/removeorder/any id
		@DeleteMapping("/removeorder/{id}")
		public String removeOrder(@PathVariable("id") int id) 
		{
			restTemplate.delete("http://Order-Service/order/delete/" +id , String.class);
			return "Order is successfully Cancelled " + id;
		}
}
