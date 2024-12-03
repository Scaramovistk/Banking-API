package com.capgemini.app.Entity;

import java.math.BigDecimal;

public class Transaction {

	private String _transactionID;
	private BigDecimal _amount;

	public Transaction() {
		this._transactionID = "";
		this._amount = BigDecimal.ZERO;
	}

	public Transaction(String transactionID, BigDecimal amount) {
		if (transactionID == null) {
			throw new IllegalArgumentException("Transaction ID cannot be null");
		}
		if (amount == null) {
			throw new IllegalArgumentException("Amount cannot be null");
		}
		this._transactionID = transactionID;
		this._amount = amount;
	}

	public String getTransactionID() {
		return _transactionID;
	}

	public void setTransactionID(String transactionID) {
		if (transactionID == null) {
			throw new IllegalArgumentException("Transaction ID cannot be null");
		}
		this._transactionID = transactionID;
	}

	public BigDecimal getAmount() {
		return _amount;
	}

	public void setAmount(BigDecimal amount) {
		this._amount = amount;
	}
}
