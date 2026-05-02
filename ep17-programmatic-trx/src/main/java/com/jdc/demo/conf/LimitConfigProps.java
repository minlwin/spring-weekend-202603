package com.jdc.demo.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.limit")
public class LimitConfigProps {

	private int minBalance;
}
