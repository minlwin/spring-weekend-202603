package com.jdc.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CalculatorAspect {

	@Pointcut("execution(int com.jdc.demo.service.Calculator.divide(int, int))")
	public void targetMethod() {}
	
	@Before("targetMethod()")
	public void useJoinPoint(JoinPoint jp) {
		System.out.println("----------- JoinPoint");
		var signature = jp.getSignature();
		System.out.println(signature.getDeclaringTypeName());
		System.out.println(signature.getModifiers());
		System.out.println(signature.getName());
		System.out.println(signature.toLongString());
		System.out.println(signature.toShortString());
		
		var args = jp.getArgs();
		for(var i = 0; i < args.length; i ++) {
			System.out.println("Arg %d : %s".formatted(i + 1, args[i]));
		}
	}
	
	@After("targetMethod() && args(first, second)")
	public void usingArgs(int first, int second) {
		System.out.println("----------- After Advice");
		System.out.println("Value   : %d".formatted(first));
		System.out.println("Divisor : %d".formatted(second));
	}
	
	@After("targetMethod() && args(.., last)")
	public void usingLastArgs(int last) {
		System.out.println("----------- After Advice Last");
		System.out.println("Last   : %d".formatted(last));
	}
	
	@AfterReturning(pointcut = "targetMethod() && args(first, second)", returning = "result")
	public void usingResult(int first, int second, int result) {
		System.out.println("----------- After Returning");
		System.out.println("%d / %d = %d".formatted(first, second, result));
	}
	
	@AfterThrowing(pointcut = "targetMethod() && args(first, second)", throwing = "ex")
	public void usingException(int first, int second, RuntimeException ex) {
		System.out.println("----------- After Throwing");
		System.out.println("Value   : %d".formatted(first));
		System.out.println("Divisor : %d".formatted(second));
		System.out.println("Exception : %s".formatted(ex.getMessage()));
	}
}
