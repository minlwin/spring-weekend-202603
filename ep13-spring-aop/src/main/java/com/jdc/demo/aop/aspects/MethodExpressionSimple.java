package com.jdc.demo.aop.aspects;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Aspect
@Configuration
@Profile("method-expression-simple")
public class MethodExpressionSimple {

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
	
	@Before("anyReturnTypes()")
	public void anyReturnTypesAdvice() {
		System.out.println("........... Before Any Return Type Method.");
	}
		
	@Before("voidReturnType()")
	public void voidReturnTypeAdvice() {
		System.out.println("........... Before Void Return Type Method.");
	}

	@Before("specificReturnType()")
	public void specificReturnTypeAdvice() {
		System.out.println("........... Before Specific Return Type Method.");
	}
	
	@Before("inServicePackage()")
	public void inServicePackageAdvice() {
		System.out.println("........... Before In Service Packages.");
	}

	@Before("clasStartWithPayment()")
	public void clasStartWithPaymentAdvice() {
		System.out.println("........... Before Class Start with Payment.");
	}
	
	@Before("classEndWithProvider()")
	public void classEndWithProviderAdvice() {
		System.out.println("........... Before Class End with Provider.");
	}
	
	@Before("methodsStartWithPaid()")
	public void methodsStartWithPaidAdvice() {
		System.out.println("........... Before Method start with Paid.");
	}
	
	@Before("methodsEndWithCallback()")
	public void methodsEndWithCallbackAdvice() {
		System.out.println("........... Before Method End with Callback.");
	}

	@Before("methodsWithNoParam()")
	public void methodsWithNoParamAdvice() {
		System.out.println("........... Before Method with No Params.");
	}

	@Before("methodsWithOneSpecificParam()")
	public void methodsWithOneSpecificParamAdvice() {
		System.out.println("........... Before Method with One Specific Params.");
	}

	@Before("methodsWithStringAndIntParam()")
	public void methodsWithStringAndIntParamAdvice() {
		System.out.println("........... Before Method with String And Int Params.");
	}

	@Before("methodsWithAnyOneParam()")
	public void methodsWithAnyOneParamAdvice(Joinpoint jp) {
		System.out.println("........... Before Method with Any One Params.");
	}
	
	
}
