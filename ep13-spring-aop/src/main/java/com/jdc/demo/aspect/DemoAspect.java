package com.jdc.demo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class DemoAspect {
	
	@Pointcut("within(com.jdc.demo.service.*)")
	public void serviceClasses() {}
	
	@Pointcut("execution(void *(..))")
	public void voidMethods() {}
	
	@Before("serviceClasses() and voidMethods()")
	public void beforeLog() {
		System.out.println("Starting service method ...");
	}
}
