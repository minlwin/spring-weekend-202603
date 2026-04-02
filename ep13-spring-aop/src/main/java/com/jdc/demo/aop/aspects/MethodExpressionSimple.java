package com.jdc.demo.aop.aspects;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Aspect
@Configuration
@Profile("method-expression-simple")
public class MethodExpressionSimple {

	@Before("anyReturnTypes()")
	public void anyReturnTypes() {
		System.out.println("........... Before Any Return Type Method.");
	}
		
	@Before("voidReturnType()")
	public void voidReturnType() {
		System.out.println("........... Before Void Return Type Method.");
	}

	@Before("specificReturnType()")
	public void specificReturnType() {
		System.out.println("........... Before Specific Return Type Method.");
	}
	
	@Before("inServicePackage()")
	public void inServicePackage() {
		System.out.println("........... Before In Service Packages.");
	}

	@Before("clasStartWithPayment()")
	public void clasStartWithPayment() {
		System.out.println("........... Before Class Start with Payment.");
	}
	
	@Before("classEndWithProvider()")
	public void classEndWithProvider() {
		System.out.println("........... Before Class End with Provider.");
	}
	
	@Before("methodsStartWithPaid()")
	public void methodsStartWithPaid() {
		System.out.println("........... Before Method start with Paid.");
	}
	
	@Before("methodsEndWithCallback()")
	public void methodsEndWithCallback() {
		System.out.println("........... Before Method End with Callback.");
	}

	@Before("methodsWithNoParam()")
	public void methodsWithNoParam() {
		System.out.println("........... Before Method with No Params.");
	}

	@Before("methodsWithOneSpecificParam()")
	public void methodsWithOneSpecificParam() {
		System.out.println("........... Before Method with One Specific Params.");
	}

	@Before("methodsWithStringAndIntParam()")
	public void methodsWithStringAndIntParam() {
		System.out.println("........... Before Method with String And Int Params.");
	}

	@Before("methodsWithAnyOneParam()")
	public void methodsWithAnyOneParam(Joinpoint jp) {
		System.out.println("........... Before Method with Any One Params.");
	}
}
