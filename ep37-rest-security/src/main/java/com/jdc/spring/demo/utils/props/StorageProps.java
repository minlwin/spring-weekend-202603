package com.jdc.spring.demo.utils.props;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "app.storage")
public class StorageProps {

	private String path;
}
