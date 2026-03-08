package com.jdc;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.jdc.hello.HelloBean;

public class ApplicationMain {

	public static void main(String[] args) {
		
		var context = new AnnotationConfigApplicationContext();
		
		context.scan("com.jdc.hello");
		context.refresh();
		
		var helloBean = context.getBean(HelloBean.class);
		helloBean.sayHello();
		
		var subjects = context.getBean("subjects", List.class);
		
		for(var subject : subjects) {
			System.out.println(subject);
		}
		
		context.close();
	}
}
