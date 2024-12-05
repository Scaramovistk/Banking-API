package com.capgemini.app.Abstract;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import com.capgemini.app.Abstract.Account;
import com.capgemini.app.Entity.Transaction;

public abstract class Account {
	protected UUID _customerID;
	protected String _name;
	protected String _surname;
	protected BigDecimal _balance;
	protected List<Transaction> _ledger = new LinkedList<>();

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

	public List<Transaction> getLedger() {
		return _ledger;
	}

	public void setLedger(List<Transaction> ledger) {
		this._ledger = ledger;
	}

	public abstract void addTransaction(Transaction transaction);
}