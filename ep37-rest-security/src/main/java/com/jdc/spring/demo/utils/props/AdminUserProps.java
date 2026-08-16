package com.jdc.spring.demo.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.admin.user")
public class AdminUserProps {

	private String name;
	private String password;
	private String email;
}
