/*
 * package com.ondemandcarwash.service;
 * 
 * import java.util.ArrayList;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.security.core.userdetails.User; import
 * org.springframework.security.core.userdetails.UserDetails; import
 * org.springframework.security.core.userdetails.UserDetailsService; import
 * org.springframework.security.core.userdetails.UsernameNotFoundException;
 * import org.springframework.stereotype.Service;
 * 
 * import com.ondemandcarwash.model.Customer; import
 * com.ondemandcarwash.repository.CustomerRepository;
 * 
 * @Service public class MyUserDetailsService implements UserDetailsService {
 * 
 * @Autowired private CustomerRepository customerRepository;
 * 
 * @Override public UserDetails loadUserByUsername(String name) throws
 * UsernameNotFoundException { Customer
 * user=customerRepository.findByName(name);
 * 
 * return new User(user.getName(),user.getPassword(),new ArrayList<>()); }
 * 
 * 
 * 
 * }
 */