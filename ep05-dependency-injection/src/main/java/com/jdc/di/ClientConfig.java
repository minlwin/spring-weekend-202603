package com.jdc.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.Priority;

@Configuration
public class ClientConfig {

	@Bean
	@Priority(100)
	MyClient myClient() {
		return () -> "Hello form Lambda";
	}
}
