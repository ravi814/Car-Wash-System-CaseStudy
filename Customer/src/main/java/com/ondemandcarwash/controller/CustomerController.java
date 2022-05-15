package com.ondemandcarwash.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
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

//import com.ondemandcarwash.config.JwtUtil;
//import com.ondemandcarwash.model.AuthenticationRequest;
//import com.ondemandcarwash.model.AuthenticationResponse;
import com.ondemandcarwash.model.Customer;
import com.ondemandcarwash.model.CustomerAuthResponse;
import com.ondemandcarwash.model.Order;
import com.ondemandcarwash.model.Ratings;
import com.ondemandcarwash.model.WashPack;
import com.ondemandcarwash.repository.CustomerRepository;
import com.ondemandcarwash.service.CustomerService;
//import com.ondemandcarwash.service.MyUserDetailsService;


@RestController

@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private CustomerService customerService;
	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;

//	@Autowired
//	private JwtUtil jwtTokenUtil;
	
	
	@PostMapping("/auth")
	private ResponseEntity<?> authCustomer(@RequestBody Customer customer)
	{
		String email = customer.getEmail();
		String password = customer.getPassword();
		try 
		{
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
				
		} catch (Exception e)
		{
				
			return ResponseEntity.ok(new CustomerAuthResponse("Error during  customer Authentication "+ email));
		}
		
		return ResponseEntity.ok(new CustomerAuthResponse("Successfully Authenticated customer "+ email));
			
	}

//	@Autowired
//	private MyUserDetailsService userDetailsService;
	
	
	
//	@PostMapping("/authenticate")
//	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

//		try {
//			authenticationManager.authenticate(
//					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
//			);
//		}
//		catch (BadCredentialsException e) {
//			throw new Exception("Incorrect username or password", e);
//		}


//		final UserDetails userDetails = userDetailsService
//				.loadUserByUsername(authenticationRequest.getUsername());
//
//		final String jwt = jwtTokenUtil.generateToken(userDetails);

//		return ResponseEntity.ok(new AuthenticationResponse(jwt));
//	 }
	
	
	// welcome message for Customer
	@GetMapping("/")
	public String message() 
	{
		return "Hello! WELCOME to Customer page";
	}
	
	// adding customer
	@PostMapping("/addcustomer")
	public Customer saveCustomer(@RequestBody Customer customer) 
	{
		return customerService.addCustomer(customer);
	}
	
	// Getting customers
	@GetMapping("/allcustomers")
	public List<Customer> findAllCustomers()
	{
		return customerService.getCustomers();
	}
	
	// Getting customers using id
	@GetMapping("/allcustomers/{id}")
	public Optional<Customer> getCustomerById(@PathVariable int id)
	{
		return customerRepository.findById(id);
	}
	
	// updating customer with id
	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateCustomer(@PathVariable int id, @RequestBody Customer customer)
	{
		customerRepository.save(customer);
		return new ResponseEntity<Object>("Customer updated successfully with id " +id, HttpStatus.OK);
			
	}
	
	// Deleting customer with id
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCustomer (@PathVariable int id)
	{
			customerService.deleteById(id);
			return new ResponseEntity<Object>("user deleted with id"+id,HttpStatus.OK);
		
	}
	
	
	
// order ---------------------------------------------
	
	
	// creating order
	@PostMapping("/addorder") 
	public String addOrder (@RequestBody Order order) 
	{
	  return restTemplate.postForObject("http://Order-Service/order/addorder", order , String.class);
	}
	
	// for Deleting Order
	@DeleteMapping("/cancelorder/{id}")
	public String deleteOrder(@PathVariable("id") int id) 
	{
		restTemplate.delete("http://Order-Service/order/delete/" +id , String.class);
		return "Your Order is successfully Canceled " + id;
	}
	
	
     // Getting all packs
	@GetMapping("/allpacks")
	public List<WashPack> getAllPack()
	{
		String baseurl="http://Admin-Service/admin/allpacks";
		WashPack[] allwashpack=restTemplate.getForObject(baseurl, WashPack[].class);
		
		return Arrays.asList(allwashpack);
	}
	
	
    // Customer can give rating
	@PostMapping("/addrating") 
	public String addRating(@RequestBody Ratings rating) 
	{
	  return restTemplate.postForObject("http://Admin-Service/admin/addrating", rating , String.class);
	}

}
