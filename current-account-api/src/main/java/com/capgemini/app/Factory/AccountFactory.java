package com.capgemini.app.Factory;

import java.util.UUID;

import com.capgemini.app.Entity.Account;

public class AccountFactory {

	public AccountFactory() {

	}
	
	public Account createAccount(UUID customerID, String name, String surname)
	{
		// Checker if ID is valid
		Account new_account = new Account(customerID, surname, surname);
			return new_account;
		
	}


}