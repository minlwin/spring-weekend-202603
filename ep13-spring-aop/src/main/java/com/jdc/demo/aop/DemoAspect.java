package com.jdc.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Aspect
@Configuration
@Profile("getting-start")
public class DemoAspect {
	
	// Scope Class
	@Pointcut("within(com.jdc.demo.service.*)")
	public void serviceClasses() {}
	
	// Kind Method
	@Pointcut("execution(void *(..))")
	public void voidMethods() {}
	
	@Before("serviceClasses() and voidMethods()")
	public void beforeLog() {
		System.out.println("Starting service method ...");
	}
}
