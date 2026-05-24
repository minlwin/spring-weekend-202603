package com.jdc.demo.entity.master;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Consumer extends WalletOwner {

	private String nrc;
	private String email;
}
