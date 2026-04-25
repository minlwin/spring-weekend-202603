package com.jdc.demo.advisor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.jspecify.annotations.Nullable;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggingAdvice implements MethodInterceptor{

	@Override
	public @Nullable Object invoke(MethodInvocation invocation) throws Throwable {
		
		try {
			// Before		
			log.info("Start - {}#{}", invocation.getClass(), invocation.getMethod());
			var result = invocation.proceed();
			// After Returning
			log.info("Result of - {}#{} - {}", invocation.getClass(), invocation.getMethod(), result);
			return result;
		} catch (Exception e) {
			// After Throwing
			log.error("Error Message : {}", e.getMessage());
			throw e;
		} finally {
			// After
			log.info("End - {}#{}", invocation.getClass(), invocation.getMethod());
		}
	}

}
