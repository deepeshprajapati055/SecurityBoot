package com.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {

    @Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
    
    @Bean
    public UserDetailsService userDetailsService() {
    	UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("ADMIN")
    			.build();
    	UserDetails user = User.withUsername("user").password(passwordEncoder().encode("12345")).roles("USER")
    			.build();
    	
    	return new InMemoryUserDetailsManager(admin, user);
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    	httpSecurity.csrf()
    	.disable()
    	.authorizeHttpRequests()
    	.requestMatchers("/admin").hasRole("ADMIN")
    	.requestMatchers("/user").hasRole("USER")
    	.requestMatchers("/").permitAll()
    	.anyRequest()
    	.authenticated()
    	.and()
    	.formLogin();
    	
    	return httpSecurity.build();
    	
    }
    
}
