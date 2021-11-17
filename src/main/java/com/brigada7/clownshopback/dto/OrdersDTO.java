package com.brigada7.clownshopback.dto;


import lombok.*;

import javax.persistence.Column;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {

	private Long productId;
	private Integer quantity;
	private String phone;
	private String address;
	private String name;
	private String surname;

}
