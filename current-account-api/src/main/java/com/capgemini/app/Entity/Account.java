package com.capgemini.app.Entity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class Account {
	private UUID _customerID;
	private String _name;
	private String _surname;
	private BigDecimal _balance;
	private List<Transaction> _ledger = new LinkedList<>();

	public Account() {
		this._customerID = UUID.randomUUID();
		this._name = "";
		this._surname = "";
		this._balance = BigDecimal.ZERO;
	}
	public Account(UUID customerID, String name, String surname) {
		this._customerID = customerID;
		this._name = name;
		this._surname = surname;
		this._balance = BigDecimal.ZERO;
	}

	public UUID getCustomerID() {
		return _customerID;
	}

	public void setCustomerID(UUID customerID) {
		this._customerID = customerID;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		this._name = name;
	}

	public String getSurname() {
		return _surname;
	}

	public void setSurname(String surname) {
		this._surname = surname;
	}

	public BigDecimal getBalance() {
		return _balance.setScale(2, RoundingMode.HALF_UP);
	}

	public void setBalance(BigDecimal balance) {
		this._balance = balance;
	}

	public List<Transaction> getLedger() {
		return _ledger;
	}

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

	private void updateBalance() {
		BigDecimal totalAmount = BigDecimal.ZERO;

		for (Transaction transaction : _ledger) {
			totalAmount = totalAmount.add(transaction.getAmount());
		}
		this.setBalance(totalAmount);
	}
}
