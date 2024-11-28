package com.capgemini.app.Accounts;

import java.util.ArrayList;
import java.util.List;
import java.math.BigDecimal;
import java.util.UUID;

public class AAccount {
	protected final UUID customerID;
	protected String name;
	protected String surname;
	protected BigDecimal balance;
	private List<BigDecimal> ledger = new ArrayList<>();
	//Transaction ID

	public AAccount(UUID customerID, BigDecimal balance) {
		this.customerID = customerID;
		this.name = "Bruce"; //Take from USERS
		this.surname = "Lee";
		this.balance = BigDecimal.ZERO;

		if (balance.compareTo(BigDecimal.ZERO) != 0)
			this.addTransaction((balance));
	}

	//Seter for new values

	public UUID getCustomerID() {
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

	// Cancel Transaction, send a negative to it and message ?
}
