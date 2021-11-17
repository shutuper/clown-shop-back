package com.brigada7.clownshopback.models.enums;

public enum InventoryStatus {
	INSTOCK,    // >15
	OUTOFSTOCK, // <=0
	LOWSTOCK,   // >0 & <=15
	EXPECTED    // <=0
}
