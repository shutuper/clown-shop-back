package com.brigada7.clownshopback.repo;

import com.brigada7.clownshopback.models.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepo extends JpaRepository<Orders, Long> {

}
