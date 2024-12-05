package com.capgemini.app.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.UUID;

import com.capgemini.app.Abstract.Account;

public class CurrentAccount extends Account {

	public CurrentAccount(UUID customerID, String name, String surname) {
		super(customerID, name, surname);
	}

	@Override
	public BigDecimal getBalance() {
		return _balance.setScale(2, RoundingMode.HALF_UP);
	}

	@Override
	public void setLedger(List<Transaction> ledger) {
		this._ledger = ledger;
		this.updateBalance();
	}

	public void addTransaction(Transaction transaction) {
		if (transaction == null) {
			throw new IllegalArgumentException("Transaction cannot be null");
		}
		_ledger.add(transaction);
		this.updateBalance();
	}

	protected void updateBalance() {
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (Transaction transaction : _ledger) {
			totalAmount = totalAmount.add(transaction.getAmount());
		}
		_balance = totalAmount;
	}
}
