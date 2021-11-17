package com.brigada7.clownshopback.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class AppUser implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private UserRole role;

	@Email
	@Column(nullable = false, unique = true)
	private String email;

	@Size(min = 8)
	@Column(nullable = false)
	private String password;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
	}

	public AppUser(UserRole role, String email, String password) {
		this.role = role;
		this.email = email;
		this.password = password;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
