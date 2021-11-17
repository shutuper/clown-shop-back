package com.brigada7.clownshopback.controllers;

import com.brigada7.clownshopback.dto.Message;
import com.brigada7.clownshopback.dto.ProductDTO;
import com.brigada7.clownshopback.models.AppUser;
import com.brigada7.clownshopback.models.Product;
import com.brigada7.clownshopback.services.ProductService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/v1/admin")
@AllArgsConstructor
public class AdminController {

	private final ProductService productService;

	@GetMapping()
	public ResponseEntity<?> isAdmin(Authentication authentication) {
		log.info("Admin entered: {}", ((AppUser) (authentication.getPrincipal())).getEmail());
		return ResponseEntity.status(HttpStatus.OK).body(new Message(true));
	}

	@PostMapping("products")
	public ResponseEntity<?> saveProduct(@Valid @RequestBody ProductDTO productDTO) {
		log.info(productDTO.toString());
		try {
			Product product = productService.saveProduct(productDTO);
			return ResponseEntity.ok().body(product.getId());
		} catch (IllegalStateException e) {
			return ResponseEntity.badRequest().build();
		} catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
	}

	@DeleteMapping("products/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable("id") Long id) {
		try {
			productService.deleteById(id);
			return ResponseEntity.ok().build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}

}
