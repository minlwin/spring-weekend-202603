package com.jdc.demo.controller;

import java.time.DayOfWeek;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.demo.domain.embeddables.Schedule;
import com.jdc.demo.domain.entity.Classes.Status;
import com.jdc.demo.domain.input.ClassesForm;
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
	String addNew(@ModelAttribute("classesForm") ClassesForm form) {
		form.addSchedule(new Schedule());
		return "classes/edit";
	}
	
	@GetMapping("edit/{id}")
	String edit(@PathVariable int id, @ModelAttribute ClassesForm form, ModelMap model) {
		var data = classesService.findById(id);
		
		form.setCourseId(data.getCourseId());
		form.setStartDate(data.getStartDate());
		form.setAvailableSeats(data.getAvailableSeats());
		form.setFees(data.getFees());
		form.setMonths(data.getMonths());
		form.setSchedules(data.getSchedules());
		
		model.put("id", id);
		
		return "classes/edit";
	}
	
	@PostMapping("edit")
	String save(
			@Validated ClassesForm form, BindingResult result,
			@RequestParam(required = false, defaultValue = "0") Integer id,
			@RequestParam(required = false) String action,
			@RequestParam(required = false) Integer deletedIndex,
			ModelMap model) {
		
		if(result.hasErrors() 
				|| StringUtils.hasLength(action)
				|| null != deletedIndex) {
			
			if("addSchedule".equals(action)) {
				form.addSchedule(new Schedule());
			}
			
			if(deletedIndex != null) {
				form.getSchedules().remove((int)deletedIndex);
				
				if(form.getSchedules().isEmpty()) {
					form.addSchedule(new Schedule());
				}
			}
			
			model.put("id", id);
			
			return "classes/edit";
		}
		
		if(id > 0) {
			classesService.update(id, form);
		} else {
			id = classesService.create(form);
		}
		
		return "redirect:/classes/%s".formatted("1");
	}

	@ModelAttribute
	void getTitle(ModelMap model) {
		model.put("title", "Classes");
		model.put("courses", courseService.search(new CourseSearch()));
		model.put("statuses", Status.values());
		model.put("days", DayOfWeek.values());
	}
	
	@ModelAttribute
	ClassesSearch search() {
		return new ClassesSearch();
	}
	
	@ModelAttribute
	ClassesForm form() {
		return new ClassesForm();
	}
}
