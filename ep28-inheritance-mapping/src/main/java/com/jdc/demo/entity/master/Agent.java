package com.jdc.demo.entity.master;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Agent extends WalletOwner {

	private String shopName;
	private String shopOwner;
}
