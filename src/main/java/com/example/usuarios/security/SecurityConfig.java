package com.example.usuarios.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.usuarios.componet.JwtFilter;

@Configuration
public class SecurityConfig {
	
	
	
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtFilter jwtFilter;


   
	
	@Bean
	public SecurityFilterChain securityFilterChainy(HttpSecurity http) {
		http
		.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		.csrf(csrf -> csrf.disable())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		.authorizeHttpRequests(auth -> auth
				.requestMatchers("/auth/**").permitAll()
				.requestMatchers("/h2-console/**").permitAll()
				.anyRequest().authenticated()
				)
		.formLogin(form -> form.disable())
		.httpBasic(basic -> basic.disable())
		.headers(headers -> headers.frameOptions(frame -> frame.disable()));
				
		return http.build();
		
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
	        AuthenticationConfiguration config) throws Exception {
	    return config.getAuthenticationManager();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
