package com.brigada7.clownshopback.models;

import com.brigada7.clownshopback.dto.OrdersDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long orderId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private AppUser appUser;

	@ManyToOne(optional = false)
	private Product product;

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private String phone;

	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String surname;

	@Column(nullable = false)
	private Boolean isPaid = false;

	public Orders(OrdersDTO orderDTO) {
		this.quantity = orderDTO.getQuantity();
		this.phone = orderDTO.getPhone();
		this.address = orderDTO.getAddress();
		this.name = orderDTO.getName();
		this.surname = orderDTO.getSurname();
	}


}
