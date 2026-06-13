package com.jdc.demo.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CommonExceptionHandler {

	@ExceptionHandler
	ModelAndView handle(AppBusinessException e) {
		var mv = new ModelAndView("pages/errors");
		mv.addObject("message", e.getMessage());
		return mv;
	}
	
	@ExceptionHandler
	ModelAndView handle(RuntimeException e) {
		e.printStackTrace();
		var mv = new ModelAndView("pages/errors");
		mv.addObject("message", "Un expected error has been happend. Please retry your operation.");
		return mv;
	}
	
}
