package com.jdc.demo.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Contact {

	private String phone;
	private String email;
	private String address;
}
