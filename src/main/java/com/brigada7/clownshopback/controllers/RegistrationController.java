package com.brigada7.clownshopback.controllers;

import com.brigada7.clownshopback.models.AppUser;
import com.brigada7.clownshopback.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {

	private final AppUserService appUserService;

	@PostMapping
	public ResponseEntity<?> registrate(
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		try {
			AppUser appuser = appUserService.registrate(email, password);
			return ResponseEntity.ok(appuser);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());


		}
	}


}
