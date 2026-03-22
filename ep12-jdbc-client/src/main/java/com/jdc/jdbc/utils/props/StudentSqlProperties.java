package com.jdc.jdbc.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.sql.student")
public class StudentSqlProperties {
	private String findById;
	private String delete;
	private String search;
	private String update;
}
