package com.jdc.shop.controller.management;

import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("management/invoices")
public class InvoiceManagementController {

	@GetMapping
	String search() {
		return "pages/management/invoice-list";
	}

	@GetMapping("{id}")
	String findById(UUID id) {
		return "pages/management/invoice-details";
	}
}
