package com.jdc.jdbc.repo.output;

public record StudentDetails(
		int id,
		String name,
		String phone,
		String email,
		long registrations) {

}
