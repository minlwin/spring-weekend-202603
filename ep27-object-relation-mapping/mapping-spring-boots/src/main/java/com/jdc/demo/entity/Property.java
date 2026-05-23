package com.jdc.demo.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class Property implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String value;
	
	public static Property get(String name, String value) {
		var data = new Property();
		data.setName(name);
		data.setValue(value);
		return data;
	}
	
}
