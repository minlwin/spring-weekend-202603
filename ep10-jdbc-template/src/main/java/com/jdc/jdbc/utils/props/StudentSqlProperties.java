package com.jdc.jdbc.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.sql.student")
public class StudentSqlProperties {
	private String findById;
	private String search;
	private String countByEmail;
	private String insert;
	private String delete;
	private String update;
}