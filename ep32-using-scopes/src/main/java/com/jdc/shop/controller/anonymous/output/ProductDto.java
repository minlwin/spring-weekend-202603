package com.jdc.shop.controller.anonymous.output;

import java.util.UUID;

import com.jdc.shop.model.entity.Product;
import com.jdc.shop.model.entity.Product_;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

	private UUID id;
	private String name;
	private String description;
	private int price;

	public static void select(CriteriaQuery<ProductDto> cq, CriteriaBuilder cb, Root<Product> root) {
		cq.select(cb.construct(ProductDto.class,
			root.get(Product_.id),
			root.get(Product_.name),
			root.get(Product_.description),
			root.get(Product_.price)
		));
	}
}
