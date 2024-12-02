package com.capgemini.app.Entity;

import java.math.BigDecimal;

public class Transaction {

	String _transactionID;
	BigDecimal _amount;

	public Transaction() {
		this._transactionID = "";
		this._amount = new BigDecimal(0);
	}

	public Transaction(String transactionID, BigDecimal amount) {
		this._transactionID = transactionID;
		this._amount = amount;
	}

	public String getTransactionID() {
		return _transactionID;
	}

	public void setTransactionID(String transactionID) {
		this._transactionID = transactionID;
	}

	public BigDecimal getAmount() {
		return _amount;
	}

	public void setAmount(BigDecimal amount) {
		this._amount = amount;
	}
}
