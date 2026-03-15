package com.jdc.circular.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BeanA {
	
	private BeanB beanB;
	
	@Autowired
	public void setBeanB(BeanB beanB) {
		this.beanB = beanB;
	}

	public BeanB getBeanB() {
		return beanB;
	}
}
