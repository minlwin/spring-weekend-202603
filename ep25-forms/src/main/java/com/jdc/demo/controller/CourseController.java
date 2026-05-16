package com.jdc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.domain.constants.CourseLevel;
import com.jdc.demo.domain.input.CourseSearch;
import com.jdc.demo.service.CourseService;

@Controller
@RequestMapping("courses")
public class CourseController {
	
	@Autowired
	private CourseService service;

	@GetMapping
	String search(ModelMap model, CourseSearch search) {
		model.put("form", search);
		model.put("result", service.search(search));
		return "courses/list";
	}

	@GetMapping("{id}")
	String findById(@PathVariable int id, ModelMap model) {
		model.put("details", service.findById(id));
		return "courses/details";
	}

	
	@ModelAttribute
	void setModel(ModelMap model) {
		model.put("title", "Courses");
		model.put("levels", CourseLevel.values());
	}
	
}
