package com.brigada7.clownshopback.controllers;

import com.brigada7.clownshopback.models.AppUser;
import com.brigada7.clownshopback.services.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/registration")
public class RegistrationController {

	private final AppUserService appUserService;

	@PostMapping
	public ResponseEntity registrate(
			@RequestParam("email") String email,
			@RequestParam("password") String password) {
		try {
			AppUser appuser = appUserService.registrate(email, password);
			return new ResponseEntity(appuser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.UNAUTHORIZED);

		}
	}

	@GetMapping("isLoggedIn")
	public ResponseEntity isLogined() {
		return new ResponseEntity(HttpStatus.OK);
	}

}
