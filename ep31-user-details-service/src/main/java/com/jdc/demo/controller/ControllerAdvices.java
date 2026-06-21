package com.jdc.demo.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttribute;

@ControllerAdvice
public class ControllerAdvices {

	@ModelAttribute(name = "memberName")
	String memberName(@SessionAttribute(name = "loginUser", required = false) String name) {
		return name;
	}
}
