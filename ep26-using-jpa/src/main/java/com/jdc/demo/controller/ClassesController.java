package com.jdc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.domain.entity.Course.Level;
import com.jdc.demo.domain.input.ClassesSearch;
import com.jdc.demo.domain.input.CourseSearch;
import com.jdc.demo.service.ClassesService;
import com.jdc.demo.service.CourseService;

@Controller
@RequestMapping("classes")
public class ClassesController {
	
	@Autowired
	private CourseService courseService;
	@Autowired
	private ClassesService classesService;

	@GetMapping
	String search(@ModelAttribute("classesSearch") ClassesSearch form, ModelMap model) {
		model.put("list", classesService.search(form));
		return "classes/list";
	}
	
	@GetMapping("{id}")
	String findById(@PathVariable int id, ModelMap model) {
		model.put("details", classesService.findById(id));
		return "classes/details";
	}
	
	@GetMapping("edit")
	String addNew() {
		return "classes/edit";
	}
	
	@GetMapping("edit/{id}")
	String edit(@PathVariable int id) {
		return "classes/edit";
	}
	
	@PostMapping("edit")
	String save() {
		return "redirect:/classes/%s".formatted("1");
	}

	@ModelAttribute
	void getTitle(ModelMap model) {
		model.put("title", "Classes");
		model.put("courses", courseService.search(new CourseSearch()));
		model.put("levels", Level.values());
	}
	
	@ModelAttribute
	ClassesSearch search() {
		return new ClassesSearch();
	}
}
