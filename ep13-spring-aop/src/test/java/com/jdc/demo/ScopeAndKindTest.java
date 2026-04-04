package com.jdc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jdc.demo.provider.PaymentProvider;
import com.jdc.demo.service.MyService;
import com.jdc.demo.service.OtherService;

@SpringBootTest
@ActiveProfiles("scope-kind")
public class ScopeAndKindTest {

	@Autowired
	private PaymentProvider provider;
	@Autowired
	private MyService myService;
	@Autowired
	private OtherService otherService;
	
	@Test
	void test() {
		provider.afterSuccessCallback("Test");
		provider.paidFees(10);

		myService.showMessage();
		myService.divide(10);
		
		otherService.doJob();
		myService.divide(0);
	}
}
