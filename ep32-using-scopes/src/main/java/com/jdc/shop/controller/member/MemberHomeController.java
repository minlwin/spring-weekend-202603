package com.jdc.shop.controller.member;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("member")
public class MemberHomeController {

	@GetMapping
	String index() {
		return "pages/member/home";
	}
}
