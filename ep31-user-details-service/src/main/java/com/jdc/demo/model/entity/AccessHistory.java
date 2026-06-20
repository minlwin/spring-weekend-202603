package com.jdc.demo.model.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class AccessHistory {

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(nullable = false)
	private String username;
	
	@Column(nullable = false)
	private LocalDateTime accessAt;	
	
	@Column(nullable = false)
	private AccessType type;

	@Column(nullable = false)
	private Status status;
	
	private String remark;
	
	
	public enum AccessType {
		Login, Logout
	}
	
	public enum Status {
		Success, Fails
	}
}
