package com.jdc.bean;

public class User {

	private String name;
	private String phone;
	private String email;

	/**
	 * Instance Factory Method
	 * 
	 * @param name
	 * @return
	 */
	public User clone(String name) {
		var user = new User();
		user.setName(name);
		user.setPhone(phone);
		user.setEmail(email);
		return user;
	}

	/**
	 * Static Factory Method
	 * 
	 * @param name
	 * @return
	 */
	public static User createWithName(String name) {
		var user = new User();
		user.setName(name);
		return user;
	}

	public User() {
	}

	public User(String name, String phone, String email) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
