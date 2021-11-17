package com.brigada7.clownshopback.repo;

import com.brigada7.clownshopback.models.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepo extends JpaRepository<AppUser, Long> {

	Optional<AppUser> findByEmail(String email);

}
