package com.jdc.demo.service;

import org.springframework.stereotype.Service;

@Service
public class OtherServiceImpl implements OtherService{

	@Override
	public void doJob() {
		System.out.println("Message for Other Service");
	}

}
