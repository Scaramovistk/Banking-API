package com.capgemini.app.Factory;

import java.util.UUID;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Repository.AccountRepository;

public class AccountFactory {
	private static AccountRepository repository = AccountRepository.getInstance();

	private AccountFactory() {
		repository = AccountRepository.getInstance();
	}

	public static Account createAccount(UUID customerID, String name, String surname) {
		if (!repository.checkID(customerID)) 
			return new Account(customerID, name, surname);
		return null; //Exception ?
	}
}
