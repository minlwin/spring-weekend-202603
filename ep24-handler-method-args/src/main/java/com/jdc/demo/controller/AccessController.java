package com.jdc.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("access")
public class AccessController {

	@GetMapping
	public String index(
			@CookieValue(name = "accessCount", required = false, defaultValue = "0") int count, 
			ModelMap model, 
			HttpServletResponse resp) {
		
		model.put("count", count);
		model.put("message", count == 0 ? "Create Cookie" : "Use Cookie");
		
		var cookie = new Cookie("accessCount", "%s".formatted(count + 1));
		resp.addCookie(cookie);
	
		return "cookie";
	}
}
