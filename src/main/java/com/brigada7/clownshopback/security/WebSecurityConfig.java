package com.brigada7.clownshopback.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import static com.brigada7.clownshopback.models.UserRole.ADMIN;
import static com.brigada7.clownshopback.models.UserRole.USER;

@Configuration
@AllArgsConstructor
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final PasswordEncoder passwordEncoder;
	private final UserDetailsService userDetailsService;

	private static final String IS_LOGGED_IN_URL = "/api/v1/auth/isLoggedIn";
	private static final String REGISTRATION_URL = "/api/v1/registration";
	private static final String PRODUCTS_URL = "/api/v1/products";
	private static final String ORDERS_URL = "/api/v1/orders";
	private static final String LOGOUT_URL = "/api/v1/logout";
	private static final String LOGIN_URL = "/api/v1/login";
	private static final String ADMIN_URL = "/api/v1/admin";
	private static final String ALL_URLS = "/**";
	private static final String USERNAME_PARAMETER = "email";

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable()//.and().cors()
				// all URLs are protected, except 'POST /login' so anonymous user can authenticate
				.authorizeRequests()
				.antMatchers(HttpMethod.POST, LOGIN_URL, REGISTRATION_URL).permitAll()
				.antMatchers(HttpMethod.GET, PRODUCTS_URL).permitAll()
				.antMatchers(ADMIN_URL, ADMIN_URL.concat(ALL_URLS)).hasRole(ADMIN.name())
				.antMatchers(LOGOUT_URL, IS_LOGGED_IN_URL, PRODUCTS_URL.concat(ALL_URLS), ORDERS_URL).hasAnyRole(ADMIN.name(), USER.name())
				.antMatchers(ALL_URLS).permitAll()
				.anyRequest()
				.authenticated()

				// 401-UNAUTHORIZED when anonymous user tries to access protected URLs
				.and()
				.exceptionHandling()
				.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))

				// standard login form that sends 204-NO_CONTENT when login is OK and 401-UNAUTHORIZED when login fails
				.and()
				.formLogin()
				.loginProcessingUrl(LOGIN_URL)

				.usernameParameter(USERNAME_PARAMETER)
				.successHandler((req, res, auth) -> res.setStatus(HttpStatus.NO_CONTENT.value()))
				.failureHandler(new SimpleUrlAuthenticationFailureHandler())

				// standard logout that sends 204-NO_CONTENT when logout is OK
				.and()
				.logout((logout) -> logout
						.invalidateHttpSession(true)
						.logoutUrl(LOGOUT_URL)
						.logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler(HttpStatus.NO_CONTENT))
				);

	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(daoAuthenticationProvider());
	}

	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder);
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}

//	@Bean
//	public CorsFilter corsFilter() {
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true);
//		config.addAllowedOrigin("*");
//		config.addAllowedHeader("*");
//		config.addAllowedMethod("*");
//		source.registerCorsConfiguration("/**", config);
//		return new CorsFilter(source);
//	}

}
