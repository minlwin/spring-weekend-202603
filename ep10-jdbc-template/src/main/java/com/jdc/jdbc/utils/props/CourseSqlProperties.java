package com.jdc.jdbc.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.sql.course")
public class CourseSqlProperties {

	private String insert;
	private String countByName;
	private String findById;
	private String search;
	private String update;
	private String delete;
}
