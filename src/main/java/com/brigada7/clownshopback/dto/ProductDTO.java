package com.brigada7.clownshopback.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long id;

	@NotEmpty
	private String description;

	@NotEmpty
	private String name;

	@Min(0)
	@Max(1_000_000)
	private Integer quantity;

	@Min(0)
	@Max(1_000_000)
	private Integer price;

	@NotEmpty
	private String category;

	private String image; // url

	private String inventoryStatus;

}
