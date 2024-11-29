package com.capgemini.app.Repository;

import java.util.HashMap;
import java.util.UUID;

import com.capgemini.app.Entity.Account;

public class AccountRepository {

	static private AccountRepository instance;
	static private HashMap <UUID, Account> accounts = new HashMap<UUID, Account>();

	private AccountRepository() {
		
	}
	
	public static AccountRepository getInstance()
	{
		if (instance == null)
			instance = new AccountRepository();
		return(instance);
	}

	public Account getAccount(UUID customerID) {
		return accounts.get(customerID);
	}

	public void saveAccount(Account account) {
		accounts.put(account.getCustomerID(), account);
	}

	public void updateAccount(Account account) {
		accounts.put(account.getCustomerID(), account);
	}

	public void deleteAccount(UUID customerID) {
		accounts.remove(customerID);
	}

	public boolean checkID(UUID customerID) {
		return accounts.containsKey(customerID);
	}


}
