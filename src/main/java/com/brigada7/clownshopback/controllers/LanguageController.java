package com.brigada7.clownshopback.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/api/v1/languages")
public class LanguageController {

	private static final int halfYearInSeconds = 60 * 60 * 24 * 182;

	private static final Map<String, String> langs = new HashMap<>() {
		{
			put("ru", "Russian");
			put("ua", "Ukrainian");
			put("en", "English");
			put("de", "German");
			put("fr", "French");
		}
	};

	@GetMapping("{lang}")
	public ResponseEntity<?> changeLanguage(@PathVariable String lang,
	                                        HttpServletResponse response) {
		log.info("User want to change lang on {}", lang);
		if (langs.containsKey(lang)) {
			Cookie cookie = new Cookie("lang", langs.get(lang));
			cookie.setMaxAge(halfYearInSeconds);
			cookie.setPath("/");
			response.addCookie(cookie);
			return new ResponseEntity(HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().build();
		}

	}

}
