package com.jdc.demo.repo;

import org.springframework.stereotype.Repository;

@Repository
public class OtherRepoImpl implements OtherRepo {

	@Override
	public void create() {
		System.out.println("OtherRepoImpl#create()");
	}

}
