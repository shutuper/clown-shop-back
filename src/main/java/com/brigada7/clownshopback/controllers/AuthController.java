package com.brigada7.clownshopback.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

	@GetMapping("isLoggedIn")
	public ResponseEntity<?> isLoggedIn() {
		return ResponseEntity.noContent().build();
	}

}

