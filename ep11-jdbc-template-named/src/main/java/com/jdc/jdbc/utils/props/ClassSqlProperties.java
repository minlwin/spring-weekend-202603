package com.jdc.jdbc.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.sql.class")
public class ClassSqlProperties {
	private String findById;
	private String delete;
	private String search;
	private String update;
}
