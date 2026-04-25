package com.jdc.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.jdc.demo.service.OtherService;

@SpringBootTest
@ActiveProfiles("contexual")
public class ContexualTest {

	@Autowired
	private OtherService service1;
	
	@Test
	public void test() {
		service1.doJob();
	}
}
