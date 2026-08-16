package com.jdc.spring.demo.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.jwt.token")
public class JwtTokenProps {

	private String issuer;
	private int accessLife;
	private int refreshLife;	
}
