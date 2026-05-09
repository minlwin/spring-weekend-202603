package com.jdc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("multi")
public class MultiController {

	@GetMapping
	public String index(ModelMap model) {
		model.put("message", "Message from the method without path mapping.");
		return "welcome";
	}

	@GetMapping("**")
	public String anyMethod(ModelMap model) {
		model.put("message", "Message from the method mapped with wildcard.");
		return "welcome";
	}
	
	@GetMapping("action1")
	public String action1(ModelMap model) {
		model.put("message", "Message from the method mapped with action1.");
		return "welcome";
	}
}
