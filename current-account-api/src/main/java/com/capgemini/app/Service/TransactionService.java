package com.capgemini.app.Service;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Entity.Transaction;
import com.capgemini.app.Factory.TransactionFactory;

public class TransactionService {

	public static List<Transaction> getLedger(Account account) {
		if (account == null) {
			throw new IllegalArgumentException("Account cannot be null");
		}
		return account.getLedger();
	}

	public static Transaction getTransaction(Account account, String index) {
		if (account == null || index == null) {
			throw new IllegalArgumentException("Account and index cannot be null");
		}
		List<Transaction> ledger = account.getLedger();
		for (Transaction transaction : ledger) {
			if (transaction.getTransactionID().equals(index)) {
				return transaction;
			}
		}
		return null;
	}

	public static Transaction buildTransaction(Account account, BigDecimal amount) {
		if (account == null || amount == null) {
			throw new IllegalArgumentException("Account and amount cannot be null");
		}
		Transaction transaction = TransactionFactory.createTransaction(account, amount);
		return transaction;
	}

	public static boolean addTransaction(Account account, Transaction transaction) {
		if (account == null || transaction == null) {
			throw new IllegalArgumentException("Account and transaction cannot be null");
		}
		account.addTransaction(transaction);
		return true;
	}
}
