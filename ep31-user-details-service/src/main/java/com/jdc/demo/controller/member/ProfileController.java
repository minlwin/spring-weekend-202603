  package com.jdc.demo.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.jdc.demo.model.service.MemberManagementService;

@Controller
@RequestMapping("member/profile")
public class ProfileController {
	
	@Autowired
	private MemberManagementService service;

	@GetMapping
	String index(ModelMap model) {
		model.put("data", service.getProfile());
		return "pages/member/profile";
	}
	
	@PostMapping("photo/{email}")
	@PreAuthorize("authentication.name eq #email")
	String uploadPhoto(@PathVariable String email, 
			@RequestParam MultipartFile file, RedirectAttributes redirect) {
		
		if(null == file) {
			redirect.addFlashAttribute("message", "Please select profile photo.");
			return "redirect:/member/profile";
		}
		
		service.uploadProfileImage(email, file);
		
		redirect.addFlashAttribute("message", "Your profile photo has been uploaded.");
		return "redirect:/member/profile";
	}
	
}
