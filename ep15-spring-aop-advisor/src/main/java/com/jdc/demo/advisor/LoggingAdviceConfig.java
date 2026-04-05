package com.jdc.demo.advisor;

import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoggingAdviceConfig {

	@Bean
	LoggingAdvice loggingAdvice() {
		return new LoggingAdvice();
	}
	
	@Bean
	Advisor loggingAdvisor() {		
		var advisor = new DefaultPointcutAdvisor();
		advisor.setAdvice(loggingAdvice());
		advisor.setPointcut(new AnnotationMatchingPointcut(null, LogEnable.class));
		return advisor;
	}
}
