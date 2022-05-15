package com.ondemandcarwash.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.ondemandcarwash.service.WasherService;

import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.service.contexts.SecurityContext;

//@Configuration
@SuppressWarnings("deprecation")
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private WasherService washerService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		auth.userDetailsService(washerService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		.antMatchers("/washer/addwasher", "/washer/auth", "/admin/allwashers/","/admin/removewasher/{id}/","/washer/**")
		.permitAll()
		.anyRequest()
		.authenticated().and().formLogin();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();

	}
	
	@Bean
    CorsConfigurationSource corsConfigurationSource()
	{
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedMethods(Arrays.asList("http://localhost:4200"));
        configuration.setAllowedMethods(Arrays.asList("GET","POST"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
	
//	@Autowired
//	private UserDetailsService myUserDetailsService;
//	@Autowired
//	private JwtRequestFilter jwtRequestFilter;

//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(myUserDetailsService);
//	}

	/*
	 * @Bean public PasswordEncoder passwordEncoder() { return
	 * NoOpPasswordEncoder.getInstance(); }
	 * 
	 * @Override
	 * 
	 * @Bean public AuthenticationManager authenticationManagerBean() throws
	 * Exception { return super.authenticationManagerBean(); }
	 * 
	 * @Override protected void configure(HttpSecurity httpSecurity) throws
	 * Exception { httpSecurity.csrf().disable() .authorizeRequests().antMatchers(
	 * "/swagger-resources/**", "/swagger-ui/**", "/v2/api-docs",
	 * "/washer/swagger-ui/", "/webjars/**", "/washer/authenticate",
	 * "/washer/addwasher","/washer/**").permitAll().
	 * anyRequest().authenticated().and().
	 * exceptionHandling().and().sessionManagement()
	 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	 * httpSecurity.addFilterBefore(jwtRequestFilter,
	 * UsernamePasswordAuthenticationFilter.class);
	 * 
	 * }
	 * 
	 * @SuppressWarnings("unused") private SecurityContext securityContext() {
	 * return SecurityContext.builder().securityReferences(defaultAuth()).build(); }
	 * 
	 * private List<SecurityReference> defaultAuth() { AuthorizationScope
	 * authorizationScope = new AuthorizationScope("global", "accessEverything");
	 * AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
	 * authorizationScopes[0] = authorizationScope; return
	 * Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
	 * }
	 */
	
	

}
