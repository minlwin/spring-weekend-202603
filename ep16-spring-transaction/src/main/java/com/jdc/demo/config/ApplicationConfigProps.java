package com.jdc.demo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app")
public class ApplicationConfigProps {

	private int taxRate;
	private int salePriceRate;
}
