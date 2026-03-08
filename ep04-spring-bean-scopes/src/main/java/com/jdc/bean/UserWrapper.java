package com.jdc.bean;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class UserWrapper {

	private User user;

	public void setTheUser(User user) {
		this.user = user;
	}

	public String greet() {
		return "Hello! I am %s.".formatted(user.getName());
	}

	@PostConstruct
	public void init() throws Exception {
		System.out.println(user.getName());
	}

	@PreDestroy
	public void destroy() throws Exception {
		System.out.println("%s has to destroy".formatted(user.getName()));
	}
}
