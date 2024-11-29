package com.capgemini.app.Repository;

import java.util.HashMap;
import java.util.UUID;

import com.capgemini.app.Entity.Account;

public class AccountRepository {

	static private AccountRepository instance;
	static HashMap <UUID, Account> accounts;

	private AccountRepository() {
		accounts = new HashMap<>();
	}
	
	public static AccountRepository getInstance()
	{
		if (instance == null)
			instance = new AccountRepository();
		return(instance);
	}

}