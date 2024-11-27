package com.capgemini.app;

import java.util.ArrayList;

import java.util.List;

import java.math.BigDecimal;

public class AAccount {
	protected int customerID;
	protected String name;
	protected BigDecimal balance;
	protected List<BigDecimal> ledger = new ArrayList<>();

	public AAccount(int customerID, String name, double balance) {
		this.customerID = customerID;
		this.name = name;
		this.balance = BigDecimal.valueOf(balance);
	}

	public int getCustomerID() {
		return this.customerID;
	}

	public String getName() {
		return this.name;
	}

	public double getBalance() {
		return  Math.ceil(this.balance.doubleValue() * 100) / 100;
	}

	private void updateBalance() {
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (BigDecimal transaction : ledger) {
			totalAmount = totalAmount.add(transaction);
		}
		balance = totalAmount;
	}

	public void addTransaction(BigDecimal transaction) {
		ledger.add(transaction);
		this.updateBalance();
	}

}
