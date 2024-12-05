package com.capgemini.app.Factory;

import java.util.UUID;

import com.capgemini.app.Abstract.Account;
import com.capgemini.app.Entity.CurrentAccount;
import com.capgemini.app.Repository.AccountRepository;

public class CurrentAccountFactory {
	private static AccountRepository repository = AccountRepository.getInstance();

	private CurrentAccountFactory() {
	}

	public static Account createAccount(UUID customerID, String name, String surname) {
		if (customerID == null || name == null || surname == null) {
			throw new IllegalArgumentException("Customer ID, name, and surname cannot be null");
		}
		if (!repository.checkID(customerID)) {
			return new CurrentAccount(customerID, name, surname);
		}
		return null;
	}
}
