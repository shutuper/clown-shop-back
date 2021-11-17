package com.brigada7.clownshopback.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppPasswordEncoder {

	@Bean
	public PasswordEncoder getAppPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
