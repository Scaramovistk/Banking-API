package com.capgemini.app.Entity;

import java.math.BigDecimal;

public class Transaction {

	String _transactionID;
	BigDecimal _amount;
	String _prevTxHash;

	public Transaction() {
		this._transactionID = "";
		this._amount = new BigDecimal(0);
		this._prevTxHash = "";
	}

	public Transaction(String transactionID, BigDecimal amount, String prevTxHash) {
		this._transactionID = transactionID;
		this._amount = amount;
		this._prevTxHash = prevTxHash;
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

	public String getPrevTxHash() {
		return _prevTxHash;
	}

	public void setPrevTxHash(String prevTxHash) {
		this._prevTxHash = prevTxHash;
	}
}
