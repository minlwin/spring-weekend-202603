package com.jdc.di;

public class MyClientCustom implements MyClient{

	@Override
	public String greet() {
		return "Hello From Custom Client";
	}

}
