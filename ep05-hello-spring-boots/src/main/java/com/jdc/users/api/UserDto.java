package com.jdc.users.api;

import com.jdc.users.model.User;

public record UserDto(
		int id,
		String name,
		String phone,
		String email) {

	public static UserDto from(User entity) {
		return new UserDto(entity.getId(), 
				entity.getName(), 
				entity.getPhone(), 
				entity.getEmail());
	}
}
