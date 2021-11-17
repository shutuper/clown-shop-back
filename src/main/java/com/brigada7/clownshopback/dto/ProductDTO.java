package com.brigada7.clownshopback.dto;

import com.brigada7.clownshopback.models.enums.InventoryStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private String description;

	private String name;

	private Integer quantity;

	private Integer price;

	private String category;

	private String image; // url

	private String inventoryStatus;

}
