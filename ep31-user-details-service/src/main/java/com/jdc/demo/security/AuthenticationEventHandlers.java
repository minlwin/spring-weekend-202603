package com.jdc.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;

import com.jdc.demo.model.entity.AccessHistory.AccessType;
import com.jdc.demo.model.service.AccessHistoryService;

@Component
public class AuthenticationEventHandlers {
	
	@Autowired
	private AccessHistoryService service;

	@EventListener
	void handle(AuthenticationSuccessEvent event) {
		var authentication = event.getAuthentication();
		service.success(authentication.getName(), AccessType.Login);
	}

	@EventListener
	void handle(AbstractAuthenticationFailureEvent event) {
		service.fails(event.getAuthentication().getName(), event.getException());
	}

	@EventListener
	void handle(LogoutSuccessEvent event) {
		var authentication = event.getAuthentication();
		service.success(authentication.getName(), AccessType.Logout);
	}
}
