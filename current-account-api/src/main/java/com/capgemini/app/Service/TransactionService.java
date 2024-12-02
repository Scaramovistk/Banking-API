package com.capgemini.app.Service;

import java.math.BigDecimal;
import java.util.List;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Entity.Transaction;
import com.capgemini.app.Repository.AccountRepository;

public class TransactionService {

	private static long transactionCounter = 0;

	public static List<Transaction> getLedger(Account account) {
		if (account == null) {
			return null;
		}
		return account.getLedger();
	}

	public static Transaction getTransaction(Account account, String index) {
		if (account == null) {
			return null;
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
		Transaction transaction = new Transaction();
		transaction.setTransactionID(generateTransactionId());
		transaction.setAmount(amount);
		return transaction;
	}

	public static boolean addTransaction(Account account, Transaction transaction) {
		if (account == null || transaction == null) {
			return false;
		}
		account.addTransaction(transaction);
		AccountRepository repository = AccountRepository.getInstance();
		repository.updateAccount(account);
		return true;
	}

	private static String generateTransactionId() {
		return String.valueOf(transactionCounter++);
	}
}
