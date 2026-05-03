package com.jdc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.jdc.demo.dto.UserForm;

@Controller
public class WelcomeController {

	@GetMapping("/")
	String index(ModelMap model) {
		model.put("title", "Hello Spring MVC");
		return "welcome";
	}
	
	@PostMapping
	String greet(ModelMap model, UserForm form) {
		model.put("title", "Hello Spring MVC");
		System.out.println(form);
		return "redirect:/";
	}
}
