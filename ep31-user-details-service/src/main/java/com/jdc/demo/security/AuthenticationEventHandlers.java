package com.jdc.demo.security;

import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.jdc.demo.model.entity.AccessHistory.AccessType;
import com.jdc.demo.model.repo.MemberRepo;
import com.jdc.demo.model.service.AccessHistoryService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AuthenticationEventHandlers {
	
	private final AccessHistoryService service;
	private final MemberRepo memberRepo;

	@EventListener
	void handle(AuthenticationSuccessEvent event) {
		
		// Create Access Log
		var authentication = event.getAuthentication();
		service.success(authentication.getName(), AccessType.Login);
		
		// Get Request
		var requestAttributes = RequestContextHolder.getRequestAttributes();
		if(null != requestAttributes && requestAttributes instanceof ServletRequestAttributes attrs) {
			HttpServletRequest request = attrs.getRequest();
			var session = request.getSession(true);
			
			memberRepo.findOneByAccountEmail(authentication.getName()).ifPresent(member -> {
				session.setAttribute("loginUser", member.getName());
			});
		}	
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
