package com.capgemini.app.Factory;

import java.math.BigDecimal;
import java.util.UUID;

import com.capgemini.app.Entity.Transaction;

public class TransactionFactory {
	private TransactionFactory() {
	}

	public static Transaction createTransaction(UUID accountId, BigDecimal amount) {
		if (accountId == null || amount == null) {
			throw new IllegalArgumentException("Account ID and amount cannot be null");
		}
		return new Transaction(generateTransactionId(), amount);
	}

	private static String generateTransactionId() {
		return String.valueOf(UUID.randomUUID());
	}
}
