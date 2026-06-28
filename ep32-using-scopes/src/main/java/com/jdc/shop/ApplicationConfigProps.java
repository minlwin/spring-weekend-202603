package com.jdc.shop;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "app.conf")
public class ApplicationConfigProps {

	private int maxAddress;
}
