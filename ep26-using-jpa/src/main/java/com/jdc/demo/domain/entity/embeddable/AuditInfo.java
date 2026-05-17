package com.jdc.demo.domain.entity.embeddable;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class AuditInfo {

	private LocalDateTime createAt;
	private LocalDateTime modifiedAt;
	private LocalDateTime deletedAt;
}
