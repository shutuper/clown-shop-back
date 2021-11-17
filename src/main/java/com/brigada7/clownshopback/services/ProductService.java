package com.brigada7.clownshopback.services;

import com.brigada7.clownshopback.dto.ProductDTO;
import com.brigada7.clownshopback.models.Product;
import com.brigada7.clownshopback.repo.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ProductService {

	private final ProductRepo productRepo;


	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	public Product saveProduct(ProductDTO productDTO) {
		try {
			return productRepo.save(new Product(productDTO));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			throw new IllegalStateException("Can't save this product!");
		}
	}

	public Product getProductById(Long id) {
		return productRepo.findById(id).orElseThrow(
				() -> new IllegalStateException(String.format("Product with id %s does not exist", id))
		);
	}


}
