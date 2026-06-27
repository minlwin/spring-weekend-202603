package com.jdc.demo.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.jdc.demo.model.service.MemberManagementService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NewMemberInterceptor implements HandlerInterceptor{

	private final MemberManagementService service;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		var username = SecurityContextHolder.getContext().getAuthentication().getName();
		if(SecurityUtils.hasRole("Member") 
				&& needToIntercept(request)
				&& !service.doesEditProfile(username)) {
			response.sendRedirect(request.getServletContext().getContextPath()
					.concat("/member/profile/edit/").concat(username));
			return false;
		}
		
		return true;
	}
	
	private boolean needToIntercept(HttpServletRequest request) {
		return 
			request.getServletPath().startsWith("/member")
			&& !request.getServletPath().startsWith("/member/profile/edit/");
	}
}
