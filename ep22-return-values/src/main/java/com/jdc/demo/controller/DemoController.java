package com.jdc.demo.controller;

import java.util.HashMap;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DemoController {

	@GetMapping("/")
	String returnViewName(ModelMap model) {
		model.put("message", "Return Values of Controller Method");
		return "welcome";
	}
	
	@GetMapping("model-view")
	ModelAndView returnModelAndView() {
		var model = new HashMap<String, Object>();
		model.put("message", "I will return Model and View Object.");
		return new ModelAndView("view1", model);
	}
	
	@GetMapping("view1")
	ModelMap returnModel() {
		var model = new ModelMap();
		model.put("message", "I will return Model only.");
		return model;
	}
	
	@GetMapping("view2")
	void noReturn(ModelMap model) {
		model.put("message", "I didn't return any objects.");
	}
}
