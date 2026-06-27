package com.jdc.shop.controller.anonymous;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.model.service.ProductService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("anonymous/welcome")
@RequiredArgsConstructor
public class WelcomeController {
	
	private final ProductService service;

	@GetMapping
	String index(
			@RequestParam(required = false) String category, 
			@RequestParam(required = false) String keyword, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "12") int size,
			ModelMap model) {
		model.put("result", service.search(category, keyword, page, size));
		return "pages/welcome";
	}
}
