package com.jdc.demo.controller;

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
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.demo.domain.entity.Course.Level;
import com.jdc.demo.domain.input.CourseForm;
import com.jdc.demo.service.CourseService;

@Controller
@RequestMapping("courses/edit")
public class CourseEditController {
	
	@Autowired
	private CourseService service;

	@GetMapping
	String create() {
		return "courses/edit";
	}

	@GetMapping("{id}")
	String edit(@PathVariable int id, ModelMap model) {
		var details = service.findById(id);
		var form = new CourseForm();
		form.setDetails(details);
		model.put("courseForm", form);
		model.put("id", details.getId());
		return "courses/edit";
	}
	
	@PostMapping
	String save(
			@RequestParam(required = false) Integer id,
			@Validated @ModelAttribute CourseForm form, 
			BindingResult result) {	
		if(result.hasErrors()) {
			return "courses/edit";
		}
		
		if(null == id) {
			id = service.create(form);
		} else {
			service.update(id, form);
		}
		
		return "redirect:/courses/%s".formatted(id);
	}

	@ModelAttribute
	CourseForm getForm() {
		return new CourseForm();
	}

	@ModelAttribute
	void setModel(ModelMap model) {
		model.put("title", "Courses");
		model.put("levels", Level.values());
	}
	
}
