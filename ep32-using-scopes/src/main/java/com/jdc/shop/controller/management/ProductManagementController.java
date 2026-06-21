package com.jdc.shop.controller.management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.jdc.shop.controller.management.input.ProductSearch;
import com.jdc.shop.model.service.ProductService;

@Controller
@RequestMapping("management/products")
public class ProductManagementController {
	
	private ProductService service;

	@GetMapping
	String search(ProductSearch form,
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size,
			ModelMap model) {
		model.put("result", service.search(form, page, size));
		return "pages/management/product-list";
	}

	@PostMapping
	String upload(@RequestParam MultipartFile file) {
		service.upload(file);
		return "redirect:/management/products";
	}
}
