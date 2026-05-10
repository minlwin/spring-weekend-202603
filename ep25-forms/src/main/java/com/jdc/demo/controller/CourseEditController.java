package com.jdc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.domain.constants.CourseLevel;
import com.jdc.demo.domain.input.CourseForm;

@Controller
@RequestMapping("courses/edit")
public class CourseEditController {

	@GetMapping
	String create() {
		return "courses/edit";
	}

	@GetMapping("{id}")
	String edit(@PathVariable int id) {
		return "courses/edit";
	}
	
	@PostMapping
	String save(@Validated @ModelAttribute("form") CourseForm form, BindingResult result) {	
		if(result.hasErrors()) {
			return "course/edit";
		}
		return "redirect:/courses/1";
	}

	@ModelAttribute("form")
	CourseForm getForm() {
		return new CourseForm();
	}

	@ModelAttribute
	void setModel(ModelMap model) {
		model.put("title", "Courses");
		model.put("levels", CourseLevel.values());
	}
	
}
