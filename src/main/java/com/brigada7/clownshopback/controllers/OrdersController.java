package com.brigada7.clownshopback.controllers;

import com.brigada7.clownshopback.dto.OrdersDTO;
import com.brigada7.clownshopback.models.Orders;
import com.brigada7.clownshopback.services.EmailSenderService;
import com.brigada7.clownshopback.services.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/orders")
@AllArgsConstructor
public class OrdersController {

	private final OrdersService ordersService;
	private final EmailSenderService emailSenderService;


	@PostMapping
	public ResponseEntity makeOrder(@RequestBody OrdersDTO orderDTO,
	                                Authentication authentication) {
		try {
			Orders order = ordersService.makeOrder(orderDTO, authentication);
			emailSenderService.sendOrderMessage(order, order.getAppUser().getEmail());
			return new ResponseEntity(order.getOrderId(), HttpStatus.OK);
		} catch (Exception exception) {
			return new ResponseEntity(exception.getMessage(),
					HttpStatus.BAD_REQUEST);
		}
	}

}
