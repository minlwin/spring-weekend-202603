package com.jdc.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.sql")
public class SqlConfigProps {
	
	private State state = new State();

	@Data
	public static class State {
		private String findAll;
		private String findById;
	}
}
