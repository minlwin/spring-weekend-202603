package com.jdc.demo.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member/profile")
public class ProfileController {

	@GetMapping
	String index() {
		return "pages/member/profile";
	}


	@GetMapping("edit/{email}")
	String edit(@PathVariable String email) {
		return "pages/member/profile-edit";
	}
}
