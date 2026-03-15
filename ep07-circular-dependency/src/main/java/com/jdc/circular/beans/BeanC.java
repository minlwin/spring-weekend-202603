package com.jdc.circular.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class BeanC {
	
	@Lazy
	@Autowired
	private BeanA beanA;
	
}
