package com.jdc.demo.controller.member;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("member/profile")
public class ProfileController {

	@GetMapping
	String index() {
		return "pages/member/profile";
	}


	@GetMapping("edit/{email}")
	@PreAuthorize("authentication.name eq #email")
	String edit(@PathVariable String email) {
		return "pages/member/profile-edit";
	}
	
	@PostMapping("edit/{email}")
	@PreAuthorize("authentication.name eq #email")
	String save(@PathVariable String email) {
		return "redirect:/member/profile";
	}
	
	@PostMapping("edit/{email}/photo")
	@PreAuthorize("authentication.name eq #email")
	String uploadPhoto(@PathVariable String email, 
			@RequestParam MultipartFile file) {
		return "redirect:/member/profile";
	}
}
