package com.jdc.demo.entity;

import java.time.LocalDateTime;

import com.jdc.demo.listener.AuditableListener;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
@EntityListeners(value = AuditableListener.class)
public abstract class Auditable {

	private LocalDateTime createdAt;
	private LocalDateTime lastUpdatedAt;
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public LocalDateTime getLastUpdatedAt() {
		return lastUpdatedAt;
	}
	public void setLastUpdatedAt(LocalDateTime lastUpdatedAt) {
		this.lastUpdatedAt = lastUpdatedAt;
	}

	
}