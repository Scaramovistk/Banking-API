package com.capgemini.app.Service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Factory.AccountFactory;
import com.capgemini.app.Repository.AccountRepository;

@Service
public class AccountService {

	private static AccountRepository repository = AccountRepository.getInstance();

	public static void createCurrentAccount(UUID customerID, BigDecimal balance) {
		Account newAccount = AccountFactory.createAccount(customerID, "Bruce", "Lee"); //Take User infor and replace

		//Add enum for account type
		if (!balance.equals(BigDecimal.ZERO))
			newAccount.addTransaction(balance);
		repository.saveAccount(newAccount);
	}

	public static Account getCurrentAccount(UUID customerID) {
		Account account = repository.getAccount(customerID);

		if (account == null)
			return null;
		return account;
	}

	public static void deleteAccount(UUID customerID) {
		Account account = repository.getAccount(customerID);

		if (account != null) // Check if account exists else ?
			repository.deleteAccount(customerID);
	}
}
