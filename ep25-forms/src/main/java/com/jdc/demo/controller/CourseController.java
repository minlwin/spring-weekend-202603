package com.jdc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("courses")
public class CourseController {

	@GetMapping
	String search() {
		return "courses/list";
	}

	@GetMapping("{id}")
	String findById(@PathVariable int id) {
		return "courses/details";
	}

	@GetMapping("create")
	String create() {
		return "courses/edit";
	}

	@GetMapping("{id}/edit")
	String edit(@PathVariable int id) {
		return "courses/edit";
	}
	
	@PostMapping
	String save() {
		return "redirect:/courses/1";
	}
	
	@ModelAttribute("title")
	String getTitle() {
		return "Courses";
	}
	
}
