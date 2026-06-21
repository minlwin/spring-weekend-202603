package com.jdc.shop.controller.management;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("management/products")
public class ProductManagementController {

	@GetMapping
	String search() {
		return "pages/management/product-list";
	}

	@PostMapping
	String upload() {
		return "redirect:/management/products";
	}
}
