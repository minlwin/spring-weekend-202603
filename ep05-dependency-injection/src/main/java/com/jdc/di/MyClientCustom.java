package com.jdc.di;

import org.springframework.stereotype.Component;

@Component("client")
public class MyClientCustom implements MyClient{

	@Override
	public String greet() {
		return "Hello From Custom Client";
	}

}
