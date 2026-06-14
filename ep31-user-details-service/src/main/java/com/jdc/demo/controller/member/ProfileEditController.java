package com.jdc.demo.controller.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jdc.demo.model.input.ProfileForm;
import com.jdc.demo.model.service.MemberManagementService;

@Controller
@RequestMapping("member/profile/edit/{email}")
public class ProfileEditController {
	
	@Autowired
	private MemberManagementService service;

	@GetMapping
	@PreAuthorize("authentication.name eq #email")
	String edit(@PathVariable String email) {
		return "pages/member/profile-edit";
	}
	
	@PostMapping
	@PreAuthorize("authentication.name eq #email")
	String save(@PathVariable String email, 
			@ModelAttribute("editForm") @Validated ProfileForm form, 
			BindingResult result) {
		
		if(result.hasErrors()) {
			return "pages/member/profile-edit";
		}
		
		service.updateProfile(email, form);
		
		return "redirect:/member/profile";
	}

	@ModelAttribute
	void form(@PathVariable String email, ModelMap model) {
		var member = service.findByEmail(email);
		var form = new ProfileForm();
		form.setName(member.getName());
		form.setAddress(member.getAddress());
		form.setPhone(member.getPhone());
		
		model.put("editForm", form);
		model.put("email", email);
	}
}
