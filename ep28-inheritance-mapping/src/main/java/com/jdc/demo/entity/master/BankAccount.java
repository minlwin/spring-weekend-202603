package com.jdc.demo.entity.master;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class BankAccount {
	
	@Id
	private String accountNum;
	private String bank;
	private String accountName;

	@ManyToOne(optional = false)
	private WalletOwner owner;
}
