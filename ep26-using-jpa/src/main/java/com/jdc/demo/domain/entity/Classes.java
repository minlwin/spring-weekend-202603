package com.jdc.demo.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Classes {
	
	@Id
	private int id;
	
}
