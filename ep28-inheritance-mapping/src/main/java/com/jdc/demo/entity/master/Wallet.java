package com.jdc.demo.entity.master;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class Wallet {

	@Id
	private UUID id;
	
	@Column(nullable = false)
	private BigDecimal amount;
	
	@MapsId
	@OneToOne(optional = false)
	private WalletOwner owner;
}
