package com.jdc.demo.aop.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Profile("scope-kind")
public class ScopeAndKindAspedt {

	@Pointcut("within(com.jdc.demo.provider.*) && @within(com.jdc.demo.annotation.Provider)")
	public void provider() {}
	
	@Pointcut("args(int)")
	public void intPramsMethod() {}
	
	@Before("provider() && intPramsMethod()")
	public void beforeAdvice() {
		System.out.println("......... provider() && intPramsMethod()");
	}
	
	@Around("within(com.jdc..*) && @annotation(com.jdc.demo.annotation.Logenable)")
	public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
		try {
			// Before
			System.out.println("......... Before Log");
			var result = joinPoint.proceed();
			// After Returning
			System.out.println("......... After Returning Log");
			return result;
		} catch (Throwable e) {
			// After Throwing
			System.out.println("......... After Throwing Log");
			throw e;
		} finally {
			// After
			System.out.println("......... Finally Log");
		}
	}
}
