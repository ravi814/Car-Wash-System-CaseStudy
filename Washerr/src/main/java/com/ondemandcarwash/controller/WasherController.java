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
import com.ondemandcarwash.model.Order;
import com.ondemandcarwash.model.Ratings;
import com.ondemandcarwash.model.Washer;
import com.ondemandcarwash.model.WasherAuthResponse;
import com.ondemandcarwash.repository.WasherRepository;
//import com.ondemandcarwash.service.MyUserDetailsService;
import com.ondemandcarwash.service.WasherService;

@RestController
@RequestMapping("/washer")
@CrossOrigin(origins = "*")
public class WasherController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private WasherService washerService;
	
	@Autowired
	private WasherRepository washerRepository;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	// authentication for washer.
	// If the email and password are correct it will display "Successfully Authenticated washer".
	// Else it will display "Error during  washer Authentication".
	@PostMapping("/auth")
	private ResponseEntity<?> authWasher(@RequestBody Washer washer){
		String email = washer.getEmail();
		String password = washer.getPassword();
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
				
		} catch (Exception e){
				
			return ResponseEntity.ok(new WasherAuthResponse("Error during  washer Authentication"+ email));
		}
		return ResponseEntity.ok(new WasherAuthResponse("Successfully Authenticated washer"+ email));
			
	}

//	@Autowired
//	private JwtUtil jwtTokenUtil;
	
//	@Autowired
//	private MyUserDetailsService userDetailsService;
	
	
	/*
	 * @PostMapping("/authenticate") public ResponseEntity<?>
	 * createAuthenticationToken(@RequestBody AuthenticationRequest
	 * authenticationRequest) throws Exception {
	 * 
	 * try { authenticationManager.authenticate( new
	 * UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
	 * authenticationRequest.getPassword()) ); } catch (BadCredentialsException e) {
	 * throw new Exception("Incorrect username or password", e); }
	 * 
	 * 
	 * final UserDetails userDetails = userDetailsService
	 * .loadUserByUsername(authenticationRequest.getUsername());
	 * 
	 * final String jwt = jwtTokenUtil.generateToken(userDetails);
	 * 
	 * return ResponseEntity.ok(new AuthenticationResponse(jwt)); }
	 */
	
	    //Creating/Adding Washer with :washer/addwasher
		@PostMapping("/addwasher")
		public Washer saveWasher(@RequestBody Washer washer) 
		{
			return washerService.addWasher(washer);
		}
		// welcome meaasge for washer
		@GetMapping("/")
		public String message() 
		{
			return "Hello! WELCOME to washer page";
		}
		
		
		
		//Reading all washer through washer/allwashers.
		@GetMapping("/allwashers")
		public List<Washer> findAllWashers()
		{
			return washerService.getWashers();
		}
		
		//Reading Washer by ID through washer/allwashers/"any id"
		@GetMapping("/allwashers/{id}")
		public Optional<Washer> getWasherById(@PathVariable int id)
		{
			return washerRepository.findById(id);
					
		}
		
		
		//Updating Washer Data by Id through washer/update/any id
		@PutMapping("/update/{id}")
		public ResponseEntity<Object> updateWasher(@PathVariable int id, @RequestBody Washer washer)
		{
			
				washerRepository.save(washer);
				return new ResponseEntity<Object>("Washer updated successfully with id " +id, HttpStatus.OK);
			
					
		}
		
		
		// Deleting Washer Data by Id through washer/delete/any id
		@DeleteMapping("/delete/{id}")
		public ResponseEntity<Object> deleteWasher(@PathVariable int id)
		{
			washerService.deleteById(id);
			return new ResponseEntity<Object>("Washer deleted with id "+id,HttpStatus.OK);
		}
		
		
// orders -------------------------------
		//Reading all orders through washer/allorders
		@GetMapping("/allorders")
		public List<Order> getallorders()
		{
			String baseurl="http://Order-Service/order/allorders";
			Order[] allorders=restTemplate.getForObject(baseurl, Order[].class);
			
			return Arrays.asList(allorders);
		}
		

		
// getting ratings of washer from admin	through washer/allratings 
		@GetMapping("/allratings")
		public List<Ratings> getallratings(){
			String baseurl="http://Admin-Service/admin/allratings";
			Ratings[] allratings = restTemplate.getForObject(baseurl, Ratings[].class);
			return Arrays.asList(allratings);
		}

}
