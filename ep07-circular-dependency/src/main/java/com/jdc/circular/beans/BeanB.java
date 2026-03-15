package com.jdc.circular.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanB {
	
	@Autowired
	private BeanC beanC;

	public BeanC getBeanC() {
		return beanC;
	}
}
