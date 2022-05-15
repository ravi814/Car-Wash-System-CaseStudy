package com.ondemandcarwash;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.ondemandcarwash.model.Washer;
import com.ondemandcarwash.repository.WasherRepository;
import com.ondemandcarwash.service.WasherService;

@SpringBootTest
class WasherrApplicationTests {

	@Autowired
	private WasherService service;

	@MockBean
	private WasherRepository repository;
	
	
	@Test
	public void getWashersTest()
	{
		
		when(repository.findAll()).thenReturn(Stream
				.of(new Washer(22, "xyz","xyz1@gamil","546477","Kalyan","xyz123"))
				.collect(Collectors.toList()));
		assertEquals(1, service.getWashers().size());
	}

	@Test
	public void saveWasherTest()
	{
		Washer washer = new Washer(12, "Nikhil","nikhil@gamil","986543","Thane","nikhil123");
		when(repository.save(washer)).thenReturn(washer);
		assertEquals(washer, service.addWasher(washer));
	}
	
	@Test
	public void deleteWasherTest() 
	{
		Washer washer = new Washer(12, "Nikhil","nikhil@gamil","986543","Thane","nikhil123");
		service.deleteWasher(washer);
		verify(repository, times(1)).delete(washer);
	}

}
