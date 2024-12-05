package com.capgemini.app.Entity;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.lang.NonNull;

public class AccountRequest {
	private UUID id;
	private BigDecimal balance;

	public UUID getId() {
		return id;
	}

	@NonNull
	public void setId(UUID id) {
		this.id = id;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	@NonNull
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
