package com.jdc.demo.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.domain.input.RegistrationForm;
import com.jdc.demo.domain.input.RegistrationSearch;
import com.jdc.demo.service.ClassesService;
import com.jdc.demo.service.RegistrationService;

@Controller
@RequestMapping("registrations")
public class RegistrationController {
	
	@Autowired
	private RegistrationService regService;
	@Autowired
	private ClassesService classService;

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
	String save(@PathVariable int classId,
			@Validated @ModelAttribute("registrationForm") RegistrationForm form, 
			BindingResult result) {
			
		if(result.hasErrors()) {
			return "registrations/edit";
		}
		
		var id = regService.create(classId, form);
		
		return "redirect:/registrations/%s".formatted(id);
	}

	@ModelAttribute
	void getTitle(ModelMap model, @PathVariable(required = false) Integer classId) {
		model.put("title", "Registrations");
		
		if(classId != null) {
			model.put("classInfo", classService.findById(classId));
		}
	}
	
	@ModelAttribute
	public RegistrationSearch search() {
		return new RegistrationSearch();
	}
	
	@ModelAttribute
	public RegistrationForm form() {
		return new RegistrationForm();
	}

}
