package com.ondemandcarwash.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ondemandcarwash.model.Order;
import com.ondemandcarwash.repository.OrderRepository;
import com.ondemandcarwash.service.OrderService;

@RestController
@RequestMapping("/order")
@CrossOrigin(origins = "http://localhost:4200")
public class orderController {
	
	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderRepository orderRepository;
	
	
	// welcome message
	@GetMapping("/")
	public String message() 
	{
		return "Hello! WELCOME to Order page";
	}
	
	// adding an order
	@PostMapping("/addorder")
	public String saveOrder(@RequestBody Order order) 
	{
		orderService.addOrder(order);
		return "Order is Placed with Washer and will be Proceesed soon. " + order;
	}
	
	
	// Getting all orders
	@GetMapping("/allorders")
	public List<Order> getOrder() 
	{
		return orderRepository.findAll();
	}
	
	// getting orders according to Id
	@GetMapping("/orders/{id}")
	public Optional<Order> getCustomerById(@PathVariable int id) 
	{
		return orderRepository.findById(id);

	}
	
	
	//Deleting an order
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteOrder(@PathVariable int id)
	{
		orderService.deleteById(id);
		return new ResponseEntity<Object>("Order deleted with id" + id, HttpStatus.OK);
	}

}
