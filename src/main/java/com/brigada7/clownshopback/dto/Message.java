package com.brigada7.clownshopback.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class Message {

	private String message;
	private Boolean bool;

	public Message(Boolean bool) {
		this.bool = bool;
	}
}
