package com.capgemini.app.Factory;


import java.math.BigDecimal;
import java.util.UUID;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Entity.Transaction;
import com.capgemini.app.Repository.AccountRepository;

public class TransactionFactory {
	
	private static AccountRepository repository = AccountRepository.getInstance();
	private static long transactionCounter = 0;

	private TransactionFactory() {

	}
	
	public static Transaction createTransaction(Account account, BigDecimal amount) {
		if (repository.checkID(account.getCustomerID())) {
			Transaction transaction = new Transaction(generateTransactionId(), amount);

			return transaction;
		}
		return null;
	}
	
	private static String generateTransactionId() {
		return String.valueOf(transactionCounter++ + "-" + UUID.randomUUID());
	}
}
