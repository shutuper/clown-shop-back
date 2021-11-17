package com.brigada7.clownshopback.services;

import com.brigada7.clownshopback.models.AppUser;
import com.brigada7.clownshopback.models.UserRole;
import com.brigada7.clownshopback.repo.AppUserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AppUserService {

	private final AppUserRepo appUserRepo;
	private final PasswordEncoder passwordEncoder;


	@Transactional
	public AppUser registrate(String email, String password) throws IllegalStateException {
		Optional<AppUser> appUserOpt = appUserRepo.findByEmail(email);

		if (appUserOpt.isEmpty()) {
			return appUserRepo.save(new AppUser(UserRole.USER, email, passwordEncoder.encode(password)));
		}
		throw new IllegalStateException(String.format("User with email %s already exists", email));
	}

	public AppUser getByEmail(String email) {
		return appUserRepo.findByEmail(email).orElseThrow(
				() -> new IllegalStateException(String.format(
						"User with email %s does not exist", email
				))
		);
	}

}
