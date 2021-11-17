package com.brigada7.clownshopback.services;

import com.brigada7.clownshopback.models.AppUser;
import com.brigada7.clownshopback.models.UserRole;
import com.brigada7.clownshopback.repo.AppUserRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@AllArgsConstructor
public class AppUserDetailsService implements UserDetailsService {

	private final AppUserRepo appUserRepo;
	private final PasswordEncoder passwordEncoder;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		return appUserRepo.findByEmail(email).orElseThrow(
				() -> new IllegalStateException(String.format("User with email: %s  does not exist", email))
		);
	}

	@PostConstruct
	public void init() {
		AppUser admin = new AppUser(
				UserRole.ADMIN,
				"ttatta3adpot@gmail.com",
				passwordEncoder.encode("lolka123"));

		if (appUserRepo.findByEmail(admin.getEmail()).isEmpty())
			appUserRepo.save(admin);

		AppUser user = new AppUser(
				UserRole.USER,
				"egot-xt@ukr.net",
				passwordEncoder.encode("lolka123"));

		if (appUserRepo.findByEmail(user.getEmail()).isEmpty())
			appUserRepo.save(user);


	}

}
