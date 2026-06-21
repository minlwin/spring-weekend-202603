package com.jdc.shop.controller;

import java.util.List;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.jdc.shop.controller.anonymous.output.CategoryDto;

@ControllerAdvice
public class CommonModelAttributes {

	@ModelAttribute(name = "categories")
	List<CategoryDto> categories() {
		return List.of();
	}
}
