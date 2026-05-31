package com.jdc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("classes")
public class ClassesController {

	@GetMapping
	String search() {
		return "classes/list";
	}
	
	@ModelAttribute
	void getTitle(ModelMap model) {
		model.put("title", "Classes");
	}
}
