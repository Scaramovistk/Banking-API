package com.capgemini.app.Service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Entity.Transaction;
import com.capgemini.app.Factory.AccountFactory;
import com.capgemini.app.Repository.AccountRepository;

@Service
public class AccountService {

	private static AccountRepository repository = AccountRepository.getInstance();

	public static boolean buildCurrentAccount(UUID customerID, BigDecimal balance) {
		Account newAccount = AccountFactory.createAccount(customerID, "Bjarne", "Stroustrup"); // Take User info and replace

		if (newAccount != null) {
			if (!balance.equals(BigDecimal.ZERO)) {
				Transaction transaction = TransactionService.buildTransaction(newAccount, balance);

				TransactionService.addTransaction(newAccount, transaction);
			}
			repository.saveAccount(newAccount);
			return true;
		}
		return false;
	}

	public static Account getCurrentAccount(UUID customerID) {
		Account account = repository.getAccount(customerID);
		if (account == null) {
			return null;
		}
		return account;
	}

	// Mssing updateAccount method

	public static boolean deleteAccount(UUID customerID) {
		Account account = repository.getAccount(customerID);

		if (account != null) { // Refactor
			repository.deleteAccount(customerID);
			return true;
		}
		return false;
	}
}
