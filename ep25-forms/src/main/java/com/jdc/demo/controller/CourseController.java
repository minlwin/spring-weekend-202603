package com.jdc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.domain.constants.CourseLevel;
import com.jdc.demo.domain.input.CourseSearch;

@Controller
@RequestMapping("courses")
public class CourseController {

	@GetMapping
	String search(ModelMap model, CourseSearch search) {
		System.out.println(search);
		model.put("form", search);
		return "courses/list";
	}

	@GetMapping("{id}")
	String findById(@PathVariable int id) {
		return "courses/details";
	}

	
	@ModelAttribute
	void setModel(ModelMap model) {
		model.put("title", "Courses");
		model.put("levels", CourseLevel.values());
	}
	
}
