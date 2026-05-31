package com.jdc.demo.domain.embeddables;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class Parent {

	private String name;
	private String phone;
	private String occupation;
}
