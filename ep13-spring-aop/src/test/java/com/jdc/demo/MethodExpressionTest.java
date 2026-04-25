package com.jdc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jdc.demo.provider.PaymentProvider;
import com.jdc.demo.provider.TelecomProvider;
import com.jdc.demo.repo.MyRepo;
import com.jdc.demo.repo.OtherRepo;
import com.jdc.demo.repo.PaymentRepo;
import com.jdc.demo.service.MyService;
import com.jdc.demo.service.OtherService;

@SpringBootTest
@ActiveProfiles({
	"method-expression-simple"
})
public class MethodExpressionTest {

	@Autowired
	private MyRepo myRepo;
	@Autowired
	private OtherRepo otherRepo;
	@Autowired
	private PaymentRepo paymentRepo;
	
	@Autowired
	private PaymentProvider paymentProvider;
	@Autowired
	private TelecomProvider telecomProvider;
	
	@Autowired
	private MyService myService;
	@Autowired
	private OtherService otherService;
	
	@Test
	public void test() {
		myRepo.create();
		otherRepo.create();
		paymentRepo.create();
		
		paymentProvider.afterSuccessCallback("Test");
		paymentProvider.paidFees(0);
		telecomProvider.sendMessage("Test");
		
		myService.showMessage();
		myService.showMessage("Test");
		myService.showMessageAndReturn();
		
		otherService.doJob();
	}

}
