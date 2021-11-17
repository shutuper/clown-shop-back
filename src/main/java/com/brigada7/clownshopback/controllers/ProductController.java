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
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequestMapping("/api/v1/products")
@AllArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	@GetMapping("{id}")
	public ResponseEntity<Product> getProduct(@PathVariable Long id) {
		try {
			return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}
	}




}
