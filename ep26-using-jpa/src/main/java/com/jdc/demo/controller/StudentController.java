package com.jdc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jdc.demo.domain.input.StudentSearch;
import com.jdc.demo.domain.output.StudentForRegistration;
import com.jdc.demo.service.StudentService;

@Controller
@RequestMapping("students")
public class StudentController {
	
	@Autowired
	private StudentService service;

	@GetMapping
	String search(
			@ModelAttribute("studentSearch") StudentSearch form, 
			ModelMap model) {
		model.put("list", service.search(form));
		return "students/list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable int id, ModelMap model) {
		model.put("details", service.findById(id));
		return "students/details";
	}	
	
	@ResponseBody
	@GetMapping("registration")
	StudentForRegistration findForRegistration(
			@RequestParam String name,
			@RequestParam String phone,
			@RequestParam String email) {
		return service.find(name, phone, email);
	}

	@ModelAttribute
	void getTitle(ModelMap model) {
		model.put("title", "Students");
	}

	@ModelAttribute
	public StudentSearch search() {
		return new StudentSearch();
	}

	
}
