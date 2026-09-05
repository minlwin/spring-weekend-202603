package com.jdc.product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jackson.autoconfigure.JsonMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import tools.jackson.databind.ext.javatime.deser.LocalDateDeserializer;
import tools.jackson.databind.ext.javatime.deser.LocalDateTimeDeserializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateSerializer;
import tools.jackson.databind.ext.javatime.ser.LocalDateTimeSerializer;
import tools.jackson.databind.module.SimpleModule;

@Configuration
public class ProductApiWebConfig {

	@Value("${spring.mvc.format.date}")
	private String datePattern;
	@Value("${spring.mvc.format.date-time}")
	private String dateTimePattern;
	
	@Bean
	JsonMapperBuilderCustomizer jsonMapperBuilderCustomizer() {
		return builder -> {
			var module = new SimpleModule();
			builder.addModule(module);
			
			var dateFormat = DateTimeFormatter.ofPattern(datePattern);
			var dateTimeFormat = DateTimeFormatter.ofPattern(dateTimePattern);
			
			module.addSerializer(LocalDate.class, new LocalDateSerializer(dateFormat));
			module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(dateTimeFormat));
			
			module.addDeserializer(LocalDate.class, new LocalDateDeserializer(dateFormat));
			module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(dateTimeFormat));	
		};
	}
}
