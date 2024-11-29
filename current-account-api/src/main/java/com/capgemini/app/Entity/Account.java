package com.capgemini.app.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {
	protected UUID _customerID;
	protected String _name;
	protected String _surname;
	protected BigDecimal _balance;
	private List<BigDecimal> _ledger = new ArrayList<>();

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
		return _balance;
	}

	public void setBalance(BigDecimal balance) {
		this._balance = balance;
	}

	public List<BigDecimal> getLedger() {
		return _ledger;
	}

	public void setLedger(List<BigDecimal> ledger) {
		this._ledger = ledger;
		this.updateBalance();
	}

	private void updateBalance() {
		BigDecimal totalAmount = BigDecimal.ZERO;

		for (BigDecimal transaction : _ledger) {
			totalAmount = totalAmount.add(transaction);
		}
		_balance = totalAmount;
	}

	public void addTransaction(BigDecimal transaction) {
		_ledger.add(transaction);
		this.updateBalance();
	}
}
