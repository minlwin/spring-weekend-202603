package com.jdc.demo.conf.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.sql")
public class SqlConfigProps {
	
	private CourseSql course = new CourseSql();

	@Data
	public static class CourseSql {
		private String insert;
		private String search;
		private String findById;
		private String update;
	}
}
