package com.jdc.demo.model.output;


import com.jdc.demo.model.entity.Member;

import lombok.Data;

@Data
public class ProfileDto {

	private int id;
	private String name;
	private String email;
	private String phone;
	private String address;
	private String profileImage;
	
	public static ProfileDto from(Member entity) {
		var dto = new ProfileDto();
		
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setEmail(entity.getAccount().getEmail());
		dto.setPhone(entity.getPhone());
		dto.setAddress(entity.getAddress());
		dto.setProfileImage(entity.getProfileImage());
		
		return dto;
	}
}
