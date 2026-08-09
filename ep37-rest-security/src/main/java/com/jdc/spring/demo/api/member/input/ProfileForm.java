package com.jdc.spring.demo.api.member.input;

import java.time.LocalDate;

public record ProfileForm(
		String name,
		String phone,
		String email,
		LocalDate dob) {

}
