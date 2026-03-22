package com.jdc.jdbc.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.sql.class")
public class ClassSqlProperties {

	private String findById;
}
