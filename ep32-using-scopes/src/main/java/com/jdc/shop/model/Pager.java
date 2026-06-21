package com.jdc.shop.model;

import java.util.List;

import lombok.Data;

@Data
public class Pager {

	private long count;
	private int size;
	private int page;
	
	public List<Integer> getLinks() {
		return List.of();
	}
}
