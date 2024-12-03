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
		Account newAccount = AccountFactory.createAccount(customerID, "Bjarne", "Stroustrup"); // Take User info and

		if (newAccount != null) {
			repository.saveAccount(newAccount);
			if (balance == null || balance.compareTo(BigDecimal.ZERO) < 0) {
				throw new IllegalArgumentException("Initial balance cannot be null or negative");
			}
			if (balance.compareTo(BigDecimal.ZERO) != 0) {
				Transaction transaction = TransactionService.buildTransaction(newAccount, balance);
				TransactionService.addTransaction(newAccount, transaction);
				updateAccount(newAccount);
			}
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

	public static boolean updateAccount(Account account) {
		if (account != null) {
			repository.updateAccount(account);
			return true;
		}
		return false;
	}

	public static boolean deleteAccount(UUID customerID) {
		Account account = repository.getAccount(customerID);
		if (account != null) {
			repository.deleteAccount(customerID);
			return true;
		}
		return false;
	}
}
