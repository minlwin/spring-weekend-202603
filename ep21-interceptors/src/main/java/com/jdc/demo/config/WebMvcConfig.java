package com.jdc.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jdc.demo.interceptor.DemoInterceptor;
import com.jdc.demo.interceptor.RequestIntercepter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/welcome").setViewName("welcome");
		registry.addRedirectViewController("/", "/welcome");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(demoInterceptor());
		registry.addWebRequestInterceptor(new RequestIntercepter());
	}
	
	@Bean
	DemoInterceptor demoInterceptor() {
		return new DemoInterceptor();
	}
}
