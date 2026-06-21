package com.jdc.shop.controller.management.output;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.Data;

@Data
public class CustomerListItem {

	private UUID id;
	private String name;
	private String email;
	private String phone;
	private LocalDateTime entryAt;
	private long invoiceCount;
}
