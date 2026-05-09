package com.jdc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.domain.StateService;

@Controller
@RequestMapping("state")
public class StateController {
	
	@Autowired
	private StateService service;
	
	@GetMapping
	public String list(ModelMap model) {
		model.put("list", service.findAll());
		return "state/list";
	}
	
	@GetMapping("{id}")
	public String showDetails(@PathVariable int id, ModelMap model) {
		model.put("state", service.findById(id).orElseThrow());
		return "state/details";
	}
	
}
