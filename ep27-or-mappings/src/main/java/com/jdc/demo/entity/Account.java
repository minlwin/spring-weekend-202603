package com.jdc.demo.entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(
	indexes = {
		@Index(columnList = "phone,app", unique = true)
	}
)
public class Account {

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(nullable = false)
	private String phone;
	
	@Enumerated(EnumType.STRING)
	@Column(name="app", nullable = false)
	private System system;
	
	private String password;
	private Instant deletedAt;
	
	public enum System {
		Consumer, Agent, Marchant, Admin
	}
}
