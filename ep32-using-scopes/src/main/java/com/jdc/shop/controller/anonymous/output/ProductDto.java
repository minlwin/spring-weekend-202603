package com.jdc.shop.controller.anonymous.output;

import java.util.UUID;

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
}
