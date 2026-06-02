package com.jdc.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.domain.input.RegistrationSearch;
import com.jdc.demo.service.RegistrationService;

@Controller
@RequestMapping("registrations")
public class RegistrationController {
	
	@Autowired
	private RegistrationService regService;

	@GetMapping
	String search(
			@ModelAttribute("registrationSearch") RegistrationSearch form, 
			ModelMap model) {
		model.put("list", regService.search(form));
		return "registrations/list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable UUID id, ModelMap model) {
		model.put("details", regService.findById(id));
		return "registrations/details";
	}
	
	@GetMapping("edit/{classId}")
	String edit(@PathVariable int classId) {
		return "registrations/edit";
	}

	@PostMapping("edit/{classId}")
	String save(@PathVariable int classId) {
		return "registrations/edit";
	}

	@ModelAttribute
	void getTitle(ModelMap model) {
		model.put("title", "Registrations");
	}
	
	@ModelAttribute
	public RegistrationSearch search() {
		return new RegistrationSearch();
	}

}
