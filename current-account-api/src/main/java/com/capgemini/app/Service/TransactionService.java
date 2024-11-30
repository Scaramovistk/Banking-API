package com.capgemini.app.Service;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.util.UUID;

import com.capgemini.app.Entity.Transaction;
import com.google.common.hash.Hashing;

public class TransactionService {

	public static Transaction buildTransaction(UUID customerID ,BigDecimal amount) {

		Transaction transaction = new Transaction();
		transaction.setTransactionID(generateTransactionId());
		transaction.setAmount(amount);
		transaction.setPrevTxHash(generatePrevTxHash()); //GEt from repository 

		return transaction;
	}

	private static String generateTransactionId() {
		String timestamp = String.valueOf(System.currentTimeMillis());
		String randomPart = UUID.randomUUID().toString().substring(0, 8);
		return "TXN-" + timestamp + "-" + randomPart;
	}

	private static String generatePrevTxHash() {
		return Hashing.sha256()
				.hashString(new Transaction().toString(), StandardCharsets.UTF_8) //Hash could be id + prevHash
				.toString();
	}
}
