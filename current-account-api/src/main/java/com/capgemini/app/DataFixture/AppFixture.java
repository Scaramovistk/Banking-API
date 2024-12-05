package com.capgemini.app.DataFixture;

import java.math.BigDecimal;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.capgemini.app.Abstract.Account;
import com.capgemini.app.Entity.CurrentAccount;
import com.capgemini.app.Factory.CurrentAccountFactory;
import com.capgemini.app.Factory.TransactionFactory;
import com.capgemini.app.Repository.AccountRepository;

public class AppFixture {

	static AccountRepository repository = AccountRepository.getInstance();
	private static final Logger logger = LoggerFactory.getLogger(AppFixture.class);

	public static void loadFixtureData() {
		loadAccountData();
		loadCurrentAccountData();
	}

	public static void loadAccountData() {
		Account account1 = new CurrentAccount(UUID.randomUUID(), "John", "Doe");
		Account account2 = new CurrentAccount(UUID.randomUUID(), "Jane", "Smith");
		Account account3 = new CurrentAccount(UUID.randomUUID(), "Alice", "Johnson");

		repository.saveAccount(account1);
		repository.saveAccount(account2);
		repository.saveAccount(account3);

		logger.info("Account 1 ID: {}", account1.getCustomerID());
		logger.info("Account 2 ID: {}", account2.getCustomerID());
		logger.info("Account 3 ID: {}", account3.getCustomerID());
	}

	public static void loadCurrentAccountData() {
		Account account1 = CurrentAccountFactory.createAccount(UUID.randomUUID(), "John", "Doe");
		Account account2 = CurrentAccountFactory.createAccount(UUID.randomUUID(), "Jane", "Smith");
		Account account3 = CurrentAccountFactory.createAccount(UUID.randomUUID(), "Alice", "Johnson");

		account1.addTransaction(
				TransactionFactory.createTransaction(account1.getCustomerID(), BigDecimal.valueOf(100.00)));
		account1.addTransaction(
				TransactionFactory.createTransaction(account1.getCustomerID(), BigDecimal.valueOf(50.00)));
		account1.addTransaction(
				TransactionFactory.createTransaction(account1.getCustomerID(), BigDecimal.valueOf(50.5871)));
		account1.addTransaction(
				TransactionFactory.createTransaction(account1.getCustomerID(), BigDecimal.valueOf(-50)));

		account2.addTransaction(
				TransactionFactory.createTransaction(account2.getCustomerID(), BigDecimal.valueOf(200.00)));
		account2.addTransaction(
				TransactionFactory.createTransaction(account2.getCustomerID(), BigDecimal.valueOf(75.00)));
		account2.addTransaction(
				TransactionFactory.createTransaction(account2.getCustomerID(), BigDecimal.valueOf(-375.00)));

		account3.addTransaction(
				TransactionFactory.createTransaction(account3.getCustomerID(), BigDecimal.valueOf(300.00)));
		account3.addTransaction(
				TransactionFactory.createTransaction(account3.getCustomerID(), BigDecimal.valueOf(125.00)));

		repository.saveCurrentAccount(account1);
		repository.saveCurrentAccount(account2);
		repository.saveCurrentAccount(account3);

		logger.info("CurrentAccount 1 ID: " + account1.getCustomerID());
		logger.info("CurrentAccount 2 ID: " + account2.getCustomerID());
		logger.info("CurrentAccount 3 ID: " + account3.getCustomerID());
	}
}
