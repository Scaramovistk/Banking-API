package com.capgemini.app.Accounts;

import java.util.UUID;
import java.util.HashMap;
import java.math.BigDecimal;

public class AccountService {
	static HashMap <UUID, AAccount> accounts = new HashMap<>();
	// UUID uuid = UUID.randomUUID();
	// Change way of storing accounts

	public AccountService () {

	}

	static public void createCurrentAccount (UUID customerID, BigDecimal balance) {
		//Get User Information
		// Checker if ID is valid and balance
		accounts.put(customerID, new CurrentAccount(customerID, balance));
	}

	static public AAccount getCurrentAccount (UUID customerID) {
		// if (accounts.containsKey(customerID))
			return accounts.get(customerID);
	}
}