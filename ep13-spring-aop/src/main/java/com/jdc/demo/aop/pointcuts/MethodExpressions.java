package com.jdc.demo.aop.pointcuts;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Aspect
@Configuration
@Profile("method-expression")
public class MethodExpressions {

	@Pointcut(value = "execution(* com.jdc..*.*(..))")
	public void anyReturnTypes() {}
		
	@Pointcut(value = "execution(void com.jdc..*.*(..))")
	public void voidReturnType() {}

	@Pointcut(value = "execution(int com.jdc..*.*(..))")
	public void specificReturnType() {}
	
	@Pointcut(value = "execution(* com.jdc..service.*.*(..))")
	public void inServicePackage() {}

	@Pointcut(value = "execution(* com.jdc..Payment*.*(..))")
	public void clasStartWithPayment() {}
	
	@Pointcut(value = "execution(* com.jdc..*Provider.*(..))")
	public void classEndWithProvider() {}
	
	@Pointcut(value = "execution(* com.jdc..*.paid*(..))")
	public void methodsStartWithPaid() {}
	
	@Pointcut(value = "execution(* com.jdc..*.*Callback(..))")
	public void methodsEndWithCallback() {}

	@Pointcut(value = "execution(* com.jdc..*.*())")
	public void methodsWithNoParam() {}

	@Pointcut(value = "execution(* com.jdc..*.*(int))")
	public void methodsWithOneSpecificParam() {}

	@Pointcut(value = "execution(* com.jdc..*.*(String, int))")
	public void methodsWithStringAndIntParam() {}

	@Pointcut(value = "execution(* com.jdc..*.*(*))")
	public void methodsWithAnyOneParam() {}	
}
