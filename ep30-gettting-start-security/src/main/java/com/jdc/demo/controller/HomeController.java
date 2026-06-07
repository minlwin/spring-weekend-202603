package com.jdc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

	@GetMapping
	String index(ModelMap model, HttpServletRequest request) {
		var forbidden = request.getParameter("forbidden");
		model.put("message", "1".equals(forbidden) ? "You can't access this resuource." : "Welcome to Getting Start Security.");
		return "welcome";
	}
}
