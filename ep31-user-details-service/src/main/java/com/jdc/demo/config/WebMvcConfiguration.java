package com.jdc.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jdc.demo.security.NewMemberInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{
	
	@Autowired
	private NewMemberInterceptor memberInterceptor;
	@Value("${app.storage.path}")
	private String storage;

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/welcome");
		registry.addViewController("/login").setViewName("pages/login");
		registry.addViewController("/403").setViewName("errors/403");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(memberInterceptor);
	}
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**")
			.addResourceLocations("file:%s".formatted(storage));
	}
	
}
