package com.brigada7.clownshopback.repo;

import com.brigada7.clownshopback.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {



}
