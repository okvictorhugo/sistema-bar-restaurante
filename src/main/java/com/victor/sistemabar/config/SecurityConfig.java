package com.victor.sistemabar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
	    .authorizeHttpRequests(auth -> auth
	        .requestMatchers("/login", "/css/**", "/js/**", "/imagens/**").permitAll()
	        .requestMatchers("/produtos").permitAll() 
	        .anyRequest().authenticated()
	    )
	    .formLogin(form -> form
	        .loginPage("/login")
	        .defaultSuccessUrl("/produtos", true) 
	        .permitAll()
	    )
	    .logout(logout -> logout.permitAll());
		
		return http.build();
	}
	
	@Bean
	public UserDetailsService userDetailsService(PasswordEncoder encoder) {
	    UserDetails user = User.withUsername("admin")
	        .password(encoder.encode("1234"))
	        .roles("USER")
	        .build();

	    return new InMemoryUserDetailsManager(user);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	
}
	
}
