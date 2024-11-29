package com.capgemini.app.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.UUID;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Entity.CurrentAccount;

public class AccountService {
	static HashMap <UUID, Account> accounts = new HashMap<>();
	// UUID uuid = UUID.randomUUID();
	// Change way of storing accounts

	public AccountService () {

	}

	static public void createCurrentAccount (UUID customerID, BigDecimal balance) {
		//Get User Information
		// Checker if ID is valid and balance
		accounts.put(customerID, new CurrentAccount(customerID, balance));
	}

	static public Account getCurrentAccount (UUID customerID) {
		// if (accounts.containsKey(customerID))
			return accounts.get(customerID);
	}
}

		// if (balance.compareTo(BigDecimal.ZERO) != 0)
		// 	this.addTransaction((balance));