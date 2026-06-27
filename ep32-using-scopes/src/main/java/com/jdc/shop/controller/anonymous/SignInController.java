package com.jdc.shop.controller.anonymous;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth/signin")
public class SignInController {

	@GetMapping
	String index() {
		return "pages/auth/signin";
	}
}
