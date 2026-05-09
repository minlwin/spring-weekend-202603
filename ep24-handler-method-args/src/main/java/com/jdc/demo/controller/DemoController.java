package com.jdc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;

@Controller
public class DemoController {

	@GetMapping("demo/{type}")
	String matrixDemo(
			@MatrixVariable(required = false, defaultValue = "0") int id, 
			@MatrixVariable(required = false) String name, 
			ModelMap model) {
		model.put("idValue", id);
		model.put("nameValue", name);
		return "demo";
	}
}
