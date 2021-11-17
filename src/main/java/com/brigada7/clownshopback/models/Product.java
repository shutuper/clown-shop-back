package com.brigada7.clownshopback.models;

import com.brigada7.clownshopback.dto.ProductDTO;
import com.brigada7.clownshopback.models.enums.InventoryStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private Integer price;

	@Column(nullable = false)
	private String category;

	@Column(nullable = false)
	private String image; // url

	@Size(max = 5)
	@Column(nullable = false)
	private Integer rating = 0;

	@Enumerated(EnumType.STRING)
	private InventoryStatus inventoryStatus;

	public Product(Long id) {
		this.id = id;
	}

	public Product(ProductDTO productDTO) {
		this.name = productDTO.getName();
		this.description = productDTO.getDescription();
		this.quantity = productDTO.getQuantity();
		this.category = productDTO.getCategory();
		this.image = productDTO.getImage();
		this.price = productDTO.getPrice();
		this.inventoryStatus = InventoryStatus.valueOf(
				productDTO.getInventoryStatus().toUpperCase()
		);
	}
}
