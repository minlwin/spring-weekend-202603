package com.jdc.demo.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.model.service.MemberManagementService;

@Controller
@RequestMapping("admin/members")
public class MemberManagementController {

	@Autowired
	private MemberManagementService service;
	
	@GetMapping
	String index(ModelMap model) {
		model.put("list", service.getAll());
		return "pages/admin/members";
	}
}
