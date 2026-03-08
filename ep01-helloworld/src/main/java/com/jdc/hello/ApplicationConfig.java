package com.jdc.hello;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

	@Bean
	List<String> subjects() {
		return List.of("Java", "Spring", "React");
	}
}
