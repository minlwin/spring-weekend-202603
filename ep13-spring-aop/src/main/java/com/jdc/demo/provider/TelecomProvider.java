package com.jdc.demo.provider;

import com.jdc.demo.annotation.Provider;

@Provider
public class TelecomProvider {

	public void sendMessage(String message) {
		System.out.println("TelecomProvider#sendMessage(String)");
	}
}
