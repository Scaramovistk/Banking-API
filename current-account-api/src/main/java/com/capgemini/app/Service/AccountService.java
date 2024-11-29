package com.capgemini.app.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Factory.AccountFactory;
import com.capgemini.app.Repository.AccountRepository;

public class AccountService {

	private static AccountFactory factory = new AccountFactory();
	private static AccountRepository repository = AccountRepository.getInstance();
	private static HashMap<UUID, Account> accounts = new HashMap<>();

	public static void createCurrentAccount(UUID customerID, BigDecimal balance) {
		Account newAccount = factory.createAccount(customerID, "Bruce", "Lee");
		accounts.put(customerID, newAccount);

		if (!balance.equals(BigDecimal.ZERO)) {
			newAccount.addTransaction(balance);
		}
	}

	public static Account getCurrentAccount(UUID customerID) {
		return accounts.get(customerID); // Return account if it exists
	}
}
