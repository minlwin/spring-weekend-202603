package com.jdc.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.model.service.MemberManagementService;
import com.jdc.demo.security.SecurityUtils;

@Controller
@RequestMapping("welcome")
public class WelcomeController {
	
	@Autowired
	private MemberManagementService memberService;

	@GetMapping
	String index(Authentication auth) {
		
		if(SecurityUtils.hasRole("Member") 
				&& !memberService.doesEditProfile(auth.getName())) {
			return "redirect:/member/profile/edit/$s".formatted(auth.getName());
		}
		
		return "pages/welcome";
	}
}
