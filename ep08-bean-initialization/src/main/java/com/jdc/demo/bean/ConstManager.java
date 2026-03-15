package com.jdc.demo.bean;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class ConstManager {
	
	private Properties props;
	
	@Autowired
	private ResourceLoader resourceLoader;
	
	@PostConstruct
	public void postConstruct() {
		try {
			var resource = resourceLoader.getResource("classpath:/constants.properties");
			props = new Properties();
			props.load(resource.getInputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getValue(String key) {
		return props.getProperty(key, null);
	}
}
