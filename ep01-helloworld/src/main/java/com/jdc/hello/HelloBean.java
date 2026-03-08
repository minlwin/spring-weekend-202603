package com.jdc.hello;

import org.springframework.stereotype.Component;

@Component
public class HelloBean {

	public String sayHello() {
		return "Hello Spring";
	}
}
