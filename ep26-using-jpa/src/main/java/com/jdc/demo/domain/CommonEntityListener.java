package com.jdc.demo.domain;

import java.time.LocalDateTime;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

public class CommonEntityListener {

	@PrePersist
	public void create(AbstractEntity entity) {
		entity.setCreatedAt(LocalDateTime.now());
	}
	
	@PreUpdate
	public void update(AbstractEntity entity) {
		entity.setModifiedAt(LocalDateTime.now());
	}
}
