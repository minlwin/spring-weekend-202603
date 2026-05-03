package com.jdc.demo.interceptor;

import org.jspecify.annotations.Nullable;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

public class RequestIntercepter implements WebRequestInterceptor{

	@Override
	public void preHandle(WebRequest request) throws Exception {
		System.out.println("Pre Handle from Web Request Interceptor");
	}

	@Override
	public void postHandle(WebRequest request, @Nullable ModelMap model) throws Exception {
		System.out.println("Post Handle from Web Request Interceptor");
	}

	@Override
	public void afterCompletion(WebRequest request, @Nullable Exception ex) throws Exception {
		System.out.println("After Completion from Web Request Interceptor");
	}

}
