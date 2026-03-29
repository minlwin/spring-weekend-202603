package com.jdc.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DemoAspect {

	@Before("execution(void com.jdc.demo.service.MyService.showMessage())")
	public void beforeLog() {
		System.out.println("Starting service method ...");
	}
}
