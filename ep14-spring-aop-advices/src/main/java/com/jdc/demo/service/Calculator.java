package com.jdc.demo.service;

import org.springframework.stereotype.Component;

@Component
public class Calculator {

	public int divide(int value, int divisor) {
		var result = value / divisor;
		System.out.printf("%d / %d = %d\n".formatted(value, divisor, result));
		return result;
	}
}
