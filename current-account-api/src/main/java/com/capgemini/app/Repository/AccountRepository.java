package com.capgemini.app.Repository;

import java.util.HashMap;
import java.util.UUID;

import com.capgemini.app.Entity.Account;

public class AccountRepository {

	static private AccountRepository _instance;
	static private HashMap <UUID, Account> _current_accounts = new HashMap<UUID, Account>();

	private AccountRepository() {
		
	}

	public static AccountRepository getInstance()
	{
		if (_instance == null)
			_instance = new AccountRepository();
		return(_instance);
	}
	// REplace all to CurrentAccount
	public Account getAccount(UUID customerID) {
		return _current_accounts.get(customerID);
	}

	public void saveAccount(Account account) {
		_current_accounts.put(account.getCustomerID(), account);
	}

	public void updateAccount(Account account) {
		_current_accounts.put(account.getCustomerID(), account);
	}

	public void deleteAccount(UUID customerID) {
		_current_accounts.remove(customerID);
	}

	public boolean checkID(UUID customerID) {
		return _current_accounts.containsKey(customerID);
	}
}
