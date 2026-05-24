package com.jdc.demo.entity.pk;

import jakarta.persistence.Column;

public record CustomerContactId(
		@Column(name = "customer_id", insertable = false, updatable = false)
		int customerId,
		@Column(name = "seq_number")
		int seqNumber) {

}
