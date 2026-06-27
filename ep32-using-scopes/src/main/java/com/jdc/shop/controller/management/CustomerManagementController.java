package com.jdc.shop.controller.management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.management.input.CustomerSearch;
import com.jdc.shop.model.service.CustomerService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("management/customers")
@RequiredArgsConstructor
public class CustomerManagementController {
	
	private final CustomerService service;

	@GetMapping
	String search(CustomerSearch form,
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size,
			ModelMap model) {
		model.put("result", service.search(form, page, size));
		return "pages/management/customer-list";
	}
}
