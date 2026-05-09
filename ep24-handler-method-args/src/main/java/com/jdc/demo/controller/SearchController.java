package com.jdc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.demo.domain.StateService;

@Controller
@RequestMapping("search")
public class SearchController {
	
	@Autowired
	private StateService service;

	@GetMapping
	String search( 
			ModelMap model,
			@RequestParam(required = false) String keyword, 
			@RequestParam(required = false) Integer from, 
			@RequestParam(required = false) Integer to) {
		model.put("result", service.search(keyword, from, to));
		return "search";
	}
}
