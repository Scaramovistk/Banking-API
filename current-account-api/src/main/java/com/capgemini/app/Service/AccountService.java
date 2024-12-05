package com.capgemini.app.Service;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.capgemini.app.Abstract.Account;
import com.capgemini.app.Entity.Transaction;
import com.capgemini.app.Factory.CurrentAccountFactory;
import com.capgemini.app.Repository.AccountRepository;

@Service
public class AccountService {

	private static AccountRepository repository = AccountRepository.getInstance();

	public static boolean buildCurrentAccount(UUID customerID, BigDecimal balance) {
		if (!isValidInput(customerID, balance)) {
			return false;
		}

		Account userAccount = repository.getAccount(customerID);
		Account newAccount = CurrentAccountFactory.createAccount(customerID, userAccount.getName(),
				userAccount.getSurname());
		if (newAccount != null) {
			repository.saveCurrentAccount(newAccount);
			if (balance.compareTo(BigDecimal.ZERO) != 0) {
				Transaction transaction = TransactionService.buildTransaction(newAccount, balance);
				TransactionService.addTransaction(newAccount, transaction);
				updateAccount(newAccount);
			}
			return true;
		}
		return false;
	}

	private static boolean isValidInput(UUID customerID, BigDecimal balance) {
		return customerID != null && balance != null && balance.compareTo(BigDecimal.ZERO) >= 0
				&& repository.checkAccountID(customerID);
	}

	public static Account getCurrentAccount(UUID customerID) {
		Account account = repository.getCurrentAccount(customerID);
		if (account == null) {
			return null;
		}
		return account;
	}

	public static boolean updateAccount(Account account) {
		if (account != null) {
			repository.updateCurrentAccount(account);
			return true;
		}
		return false;
	}

	public static boolean deleteAccount(UUID customerID) {
		Account account = repository.getCurrentAccount(customerID);
		if (account != null) {
			repository.deleteCurrentAccount(customerID);
			return true;
		}
		return false;
	}
}
