package com.jdc.shop.controller.management;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.shop.controller.management.input.EmployeeForm;
import com.jdc.shop.controller.management.input.EmployeeSearch;
import com.jdc.shop.model.service.EmployeeService;

@Controller
@RequestMapping("management/employees")
public class EmployeeManagementController {
	
	private EmployeeService service;

	@GetMapping
	String search(EmployeeSearch form, 
			@RequestParam(required = false, defaultValue = "0") int page, 
			@RequestParam(required = false, defaultValue = "10") int size,
			ModelMap model) {
		model.put("result", service.search(form, page, size));
		return "pages/management/employee-list";
	}

	@GetMapping("edit")
	String create() {
		return "pages/management/employee-edit";
	}

	@PostMapping("edit")
	String save(@Validated @ModelAttribute("employeeForm") EmployeeForm form, BindingResult result) {
		
		if(result.hasErrors()) {
			return "pages/management/employee-edit";
		}
		
		service.create(form);
		
		return "redirect:/management/employees";
	}
	
	@ModelAttribute
	EmployeeForm form() {
		return new EmployeeForm();
	}
}
