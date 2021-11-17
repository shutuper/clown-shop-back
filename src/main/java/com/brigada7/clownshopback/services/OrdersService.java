package com.brigada7.clownshopback.services;

import com.brigada7.clownshopback.dto.OrdersDTO;
import com.brigada7.clownshopback.models.AppUser;
import com.brigada7.clownshopback.models.Orders;
import com.brigada7.clownshopback.models.Product;
import com.brigada7.clownshopback.models.enums.InventoryStatus;
import com.brigada7.clownshopback.repo.AppUserRepo;
import com.brigada7.clownshopback.repo.OrdersRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.brigada7.clownshopback.models.enums.InventoryStatus.*;

@Slf4j
@Service
@AllArgsConstructor
public class OrdersService {

	private final OrdersRepo ordersRepo;
	private final AppUserService appUserService;
	private final ProductService productService;

	@Transactional
	public Orders makeOrder(OrdersDTO orderDTO, Authentication authentication) {
		log.info(orderDTO.toString());

		AppUser appUser = (AppUser) authentication.getPrincipal();
		log.info("User {} try to make an order", appUser.getEmail());

		AppUser user = appUserService.getByEmail(appUser.getEmail());
		Product product = productService.getProductById(orderDTO.getProductId());

		int productQ = product.getQuantity();
		if (productQ < orderDTO.getQuantity()
				|| List.of(EXPECTED, OUTOFSTOCK).contains(product.getInventoryStatus()))
			throw new IllegalStateException("Order quantity bigger than possible quantity");

		changeProductStatus(product, orderDTO.getQuantity());

		Orders order = new Orders(orderDTO);
		order.setAppUser(user);
		order.setProduct(product);

		log.info("Saving user {} order with product id {}", appUser.getEmail(), product.getId());
		return ordersRepo.save(order);
	}


	private void changeProductStatus(Product product, int orderQ) {
		int productQ = product.getQuantity();
		product.setQuantity(productQ - orderQ);
		productQ = product.getQuantity();
		if (productQ <= 0)
			product.setInventoryStatus(OUTOFSTOCK);
		else if (productQ <= 15)
			product.setInventoryStatus(LOWSTOCK);
	}

}
