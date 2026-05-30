package com.jdc.demo.listener;

import java.time.LocalDateTime;

import com.jdc.demo.entity.Auditable;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class AuditableListener {

	@PrePersist
	public void beforeCreate(Auditable entity) {
		entity.setCreatedAt(LocalDateTime.now());
	}
	
	@PreUpdate
	public void beforeUpdate(Auditable entity) {
		entity.setLastUpdatedAt(LocalDateTime.now());
	}
}
