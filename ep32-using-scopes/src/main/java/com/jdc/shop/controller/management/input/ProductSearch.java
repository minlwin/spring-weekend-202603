package com.jdc.shop.controller.management.input;

import lombok.Data;

@Data
public class ProductSearch {

	private Integer categoryId;
	private String keyword;
}
