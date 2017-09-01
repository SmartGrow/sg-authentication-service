package com.smartgrow.authentication;

import com.smartgrow.springboot.web.filter.EnableSgFilters;
import com.smartgrow.springboot.web.security.EnableSgSecurity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableSgFilters
@EnableSgSecurity
@SpringBootApplication
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}
}
