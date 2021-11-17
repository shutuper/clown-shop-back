package com.brigada7.clownshopback.controllers;

import com.brigada7.clownshopback.dto.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/shop")
public class TestMessController {

//	@GetMapping("{echo}")
//	public Message simpleAnswer(@PathVariable("echo") String echo) {
//		System.out.println(echo);
//		return new Message(
//				echo,
//				Timestamp.valueOf(LocalDateTime.now()
//				));
//	}

	@PostMapping("mes")
	public Message message(@RequestBody Message message) {
		message.setMessage("I saw it)");
		return message;
	}

//	@GetMapping("admin/lol")
//	public Message checkAuth(Authentication authentication) {
//		log.info(authentication.toString());
//		Message message = new Message(authentication.getPrincipal().toString(),
//				Timestamp.valueOf(LocalDateTime.now()));
//		return message;
//	}


}
