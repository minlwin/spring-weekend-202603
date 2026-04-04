package com.jdc.demo.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Aspect
@Configuration
@Profile("contexual")
public class ContexualAspect {

	@Before("this(com.jdc.demo.service.OtherService)")
	public void toProxy() {
		System.out.println("-------- Proxy");
	}
	
	@Before("target(com.jdc.demo.service.OtherServiceImpl)")
	public void toTarget() {
		System.out.println("-------- Target");
	}
	
}
