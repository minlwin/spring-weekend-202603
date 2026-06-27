package com.jdc.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.jdc.shop.controller.anonymous.output.CategoryDto;
import com.jdc.shop.controller.anonymous.output.ShoppingCart;
import com.jdc.shop.model.service.CategoryService;

import lombok.AllArgsConstructor;

@ControllerAdvice
@AllArgsConstructor
public class CommonModelAttributes {
	
	private final CategoryService service;

	@ModelAttribute(name = "categories")
	List<CategoryDto> categories() {
		return service.findAll();
	}
	
	@ModelAttribute(name = "cartCount")
	int getCartCount(@SessionAttribute(name = "shoppingCart", required = false) ShoppingCart cart) {
		if(null != cart) {
			return cart.count();
		}
		return 0;
	}
}
