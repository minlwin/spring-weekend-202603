package com.jdc.demo.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jdc.demo.model.service.MemberManagementService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/members")
public class MemberManagementController {

	private final MemberManagementService service;
	
	@GetMapping
	String index(ModelMap model) {
		model.put("list", service.getAll());
		return "pages/admin/members";
	}
	
	@PostMapping
	String switchStatus(@RequestParam int id) {
		service.switchStatus(id);
		return "redirect:/admin/members";
	}
}
