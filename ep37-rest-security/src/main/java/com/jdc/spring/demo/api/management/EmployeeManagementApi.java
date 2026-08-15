package com.jdc.spring.demo.api.management;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdc.spring.demo.api.management.input.EmployeeForm;
import com.jdc.spring.demo.api.management.input.EmployeeSearch;
import com.jdc.spring.demo.api.management.output.EmployeeDetails;
import com.jdc.spring.demo.api.management.output.EmployeeListItem;
import com.jdc.spring.demo.api.management.service.EmployeeService;
import com.jdc.spring.demo.model.ModificationResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("management/employees")
public class EmployeeManagementApi {
	
	private final EmployeeService service;

	@GetMapping
	List<EmployeeListItem> search(EmployeeSearch search) {
		return service.search(search);
	}
	
	@GetMapping("{id}")
	EmployeeDetails findById(@PathVariable int id) {
		return service.findById(id);
	}
	
	@PostMapping
	ModificationResult<Integer> create(@RequestBody @Validated EmployeeForm form) {
		return service.create(form);
	}

	@PutMapping("{id}")
	ModificationResult<Integer> update(@PathVariable int id, @RequestBody @Validated EmployeeForm form) {
		return service.update(id, form);
	}
}
