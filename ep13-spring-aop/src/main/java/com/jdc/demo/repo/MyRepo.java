package com.jdc.demo.repo;

import org.springframework.stereotype.Repository;

@Repository
public class MyRepo {

	public void create() {
		System.out.println("MyRepo#create()");
	}
}
