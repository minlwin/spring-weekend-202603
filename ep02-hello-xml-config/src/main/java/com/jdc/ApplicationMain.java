package com.jdc;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jdc.hello.HelloBean;

public class ApplicationMain {

	public static void main(String[] args) {
		// Instantiate Context
		var context = new ClassPathXmlApplicationContext();
		
		// Load Configuration
		context.setConfigLocation("/application.xml");
		
		// Refresh Context
		context.refresh();
		
		// Ready To Use
		var helloBean = context.getBean(HelloBean.class);
		var subjects = context.getBean("subjects", List.class);
		
		System.out.println(helloBean.sayHello());
		
		for(var subject : subjects) {
			System.out.println(subject);
		}
		
		// Close Context
		context.close();
	}
}
