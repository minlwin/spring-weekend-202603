package com.jdc.jdbc.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "app.sql.registrations")
public class RegistrationSqlProperties {

	private String insert;
	private String search;
	private String findById;
	private String delete;
	private String countByClass;
	private String countByStudent;
	private String delete;
}
