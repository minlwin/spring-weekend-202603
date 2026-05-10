package com.jdc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("registrations")
public class RegistrationController {

	@GetMapping
	String search() {
		return "registrations/list";
	}

	@ModelAttribute("title")
	String getTitle() {
		return "Registrations";
	}

}
