package com.jdc.shop.controller.anonymous.output;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;

@Data
public class ShoppingCartItem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private UUID id;
	private String product;
	private int unitPrice;
	private int quantity;
	
	public int removeOne() {
		return -- quantity;
	}

	public void addOne() {
		++ quantity;
	}
}
