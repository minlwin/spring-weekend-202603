package com.jdc.demo.model.output;

import com.jdc.demo.model.entity.Member;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberListItem {

	private int id;
	private String name;
	private String email;
	private String phone;
	private boolean activated;
	
	public MemberListItem(Member entity) {
		this.id = entity.getId();
		this.name = entity.getName();
		this.email = entity.getAccount().getEmail();
		this.phone = entity.getPhone();
		this.activated = entity.getAccount().isActivated();
	}
}
