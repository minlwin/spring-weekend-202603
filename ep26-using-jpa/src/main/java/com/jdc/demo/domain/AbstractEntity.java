package com.jdc.demo.domain;

import java.time.LocalDateTime;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@MappedSuperclass
@EntityListeners(value = CommonEntityListener.class)
public abstract class AbstractEntity {

	private LocalDateTime createdAt;
	private LocalDateTime modifiedAt;
}
