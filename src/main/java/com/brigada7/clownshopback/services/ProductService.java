package com.brigada7.clownshopback.services;

import com.brigada7.clownshopback.dto.ProductDTO;
import com.brigada7.clownshopback.models.Product;
import com.brigada7.clownshopback.repo.ProductRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.brigada7.clownshopback.models.enums.InventoryStatus.*;
import static com.brigada7.clownshopback.models.enums.InventoryStatus.INSTOCK;

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
			return productRepo.save(productDtoToProduct(productDTO));
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

	public void deleteById(Long id) {
		productRepo.deleteById(id);
	}

	private Product productDtoToProduct(ProductDTO productDTO) {
		Product product = new Product();

		if (!Objects.isNull(productDTO.getId()))
			product.setId(productDTO.getId());

		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setQuantity(productDTO.getQuantity());
		product.setCategory(productDTO.getCategory());
		product.setImage(productDTO.getImage());
		product.setPrice(productDTO.getPrice());
		product.setInventoryStatus(valueOf(
				productDTO.getInventoryStatus().toUpperCase()));

		setCorrectStatus(product);

		return product;
	}

	private void setCorrectStatus(Product product) {
		if (product.getQuantity() <= 0) {
			product.setQuantity(0);
			if (!List.of(OUTOFSTOCK, EXPECTED).contains(product.getInventoryStatus()))
				throw new IllegalStateException("Incorrect inventory status");
		} else if (product.getQuantity() <= 15) {
			product.setInventoryStatus(LOWSTOCK);
		} else
			product.setInventoryStatus(INSTOCK);
	}
}

