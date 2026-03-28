package com.jdc.jdbc.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.sql.registrations")
public class RegistrationSqlProperties {
	private String insert;
	private String search;
	private String findById;
	private String countByClass;
	private String countByStudent;
}
