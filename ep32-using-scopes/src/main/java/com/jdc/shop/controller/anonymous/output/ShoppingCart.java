package com.jdc.shop.controller.anonymous.output;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ShoppingCart implements Serializable{

	private static final long serialVersionUID = 1L;

	private Map<UUID, ShoppingCartItem> items = new LinkedHashMap<>();
	
	public List<ShoppingCartItem> getItems() {
		return new ArrayList<>(this.items.values());
	}

	public ShoppingCart remove(UUID id) {
		var item = items.get(id);
		
		if(null != item) {
			if(item.removeOne() == 0) {
				items.remove(id);
			}
		}
		
		return this;
	}

	public ShoppingCart add(ProductDto product) {
		
		var item = items.get(product.getId());
		
		if(null == item) {
			item = new ShoppingCartItem();
			item.setId(product.getId());
			item.setProduct(product.getName());
			item.setUnitPrice(product.getPrice());
			
			items.put(product.getId(), item);
		}
		
		item.addOne();

		return this;
	}

	public int getCount() {
		return items.values().stream()
				.mapToInt(a -> a.getQuantity()).sum();
	}
	
	public int getTotal() {
		return items.values().stream()
				.mapToInt(a -> a.getUnitPrice() * a.getQuantity()).sum();
	}
}
