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

	public static boolean createCurrentAccount(UUID customerID, BigDecimal balance) {
		Account newAccount = AccountFactory.createAccount(customerID, "Bruce", "Lee"); // Take User info and replace

		if (newAccount == null) {
			return false;
		}

		// Add enum for account type
		if (!balance.equals(BigDecimal.ZERO)) {
			newAccount.addTransaction(balance);
		}
		repository.saveAccount(newAccount);
		return true;
	}

	public static String getCurrentAccount(UUID customerID) {
		Account account = repository.getAccount(customerID);
		if (account == null) {
			return null;
		}
		StringBuilder str = new StringBuilder(); //Move all to a Builder
		str.append(account.getCustomerID().toString());
		str.append(" ");
		str.append(account.getName());
		str.append(" ");
		str.append(account.getSurname());
		str.append(" ");
		str.append(account.getBalance().toString());
		str.append('\n'); //Is it needed?
		// Missing transactions
		return str.toString();
	}

	public static boolean deleteAccount(UUID customerID) {
		Account account = repository.getAccount(customerID);

		if (account != null) {
			repository.deleteAccount(customerID);
			return true;
		}
		return false;
	}
}
