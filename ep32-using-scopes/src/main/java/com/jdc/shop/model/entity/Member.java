package com.jdc.shop.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.jdc.shop.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Member extends BaseEntity{

	@Id
	private UUID id;
	
	@MapsId
	@OneToOne
	private Account account;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String phone;

	private LocalDateTime entryAt;
}
