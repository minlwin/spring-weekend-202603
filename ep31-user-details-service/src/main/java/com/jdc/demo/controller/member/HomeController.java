package com.jdc.demo.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/member")
@Controller("memberHomeController")
public class HomeController {

	@GetMapping
	String index() {
		return "member/home";
	}
}
