package com.jdc.demo.entity.master;

import java.util.List;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class WalletOwner {

	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private String phone;
	
	@OneToOne(mappedBy = "owner")
	private Wallet wallet;
	
	@OneToMany(mappedBy = "owner")
	private List<BankAccount> linkBanks;
}
