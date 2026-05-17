package com.jdc.demo.entity;

import java.time.Instant;

import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AuditableEntity {

	private Instant createdAt;
	private Instant modifiedAt;
	private Instant deletedAt;
}
