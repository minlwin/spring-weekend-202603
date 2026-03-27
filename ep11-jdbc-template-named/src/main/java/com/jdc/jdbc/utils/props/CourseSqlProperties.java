package com.jdc.jdbc.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "app.sql.course")
public class CourseSqlProperties {
	private String insert;
	private String countByName;
	private String findById;
	private String search;
	private String update;
	private String delete;
}
