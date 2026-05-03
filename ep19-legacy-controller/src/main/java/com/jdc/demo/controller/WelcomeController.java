package com.jdc.demo.controller;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.jdc.demo.dto.UserForm;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component(value = "/")
public class WelcomeController implements Controller{

	@Override
	public @Nullable ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if(request.getMethod().equalsIgnoreCase("GET")) {
			var model = new ModelMap();
			model.put("title", "Legacy Spring MVC");
			return new ModelAndView("welcome", model);
		} else if (request.getMethod().equalsIgnoreCase("POST")) {
			var name = request.getParameter("name");
			var phone = request.getParameter("phone");
			
			var user = new UserForm();
			user.setName(name);
			user.setPhone(phone);
			
			System.out.println(user);
			
			response.sendRedirect("/");
		}
		
		return null;
	}

}
