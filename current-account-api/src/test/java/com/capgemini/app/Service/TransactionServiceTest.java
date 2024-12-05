package com.capgemini.app.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.app.Abstract.Account;
import com.capgemini.app.Entity.CurrentAccount;
import com.capgemini.app.Entity.Transaction;
import com.capgemini.app.Repository.AccountRepository;

public class TransactionServiceTest {

	private AccountRepository repository;

	@BeforeEach
	public void setUp() {
		repository = AccountRepository.getInstance();
	}

	@Test
	public void buildTransactionTest() {
		Account account = new CurrentAccount(UUID.randomUUID(), "Bruce", "Lee");
		Transaction transaction = TransactionService.buildTransaction(account, BigDecimal.valueOf(100));
		assertEquals(BigDecimal.valueOf(100), transaction.getAmount());
	}

	@Test
	public void addTransactionTest() {
		Account account = new CurrentAccount(UUID.randomUUID(), "Bruce", "Lee");
		repository.saveCurrentAccount(account);

		Transaction transaction = TransactionService.buildTransaction(account, BigDecimal.valueOf(100));
		boolean result = TransactionService.addTransaction(account, transaction);

		assertTrue(result);
		assertEquals(BigDecimal.valueOf(100).setScale(2), account.getBalance());
	}

	@Test
	public void addMultipleTransactionsTest() {
		Account account = new CurrentAccount(UUID.randomUUID(), "Bruce", "Lee");
		repository.saveCurrentAccount(account);

		Transaction transaction1 = TransactionService.buildTransaction(account, BigDecimal.valueOf(100));
		Transaction transaction2 = TransactionService.buildTransaction(account, BigDecimal.valueOf(50));
		TransactionService.addTransaction(account, transaction1);
		TransactionService.addTransaction(account, transaction2);

		assertEquals(BigDecimal.valueOf(150).setScale(2), account.getBalance());
	}

	@Test
	public void addTransactionToNullAccountTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			Transaction transaction = TransactionService.buildTransaction(null, BigDecimal.valueOf(100));
			TransactionService.addTransaction(null, transaction);
		});
	}

	@Test
	public void addNullTransactionTest() {
		Account account = new CurrentAccount(UUID.randomUUID(), "Bruce", "Lee");
		repository.saveCurrentAccount(account);

		assertThrows(IllegalArgumentException.class, () -> {
			TransactionService.addTransaction(account, null);
		});
	}

	@Test
	public void buildTransactionWithNullAmountTest() {
		Account account = new CurrentAccount(UUID.randomUUID(), "Bruce", "Lee");
		assertThrows(IllegalArgumentException.class, () -> {
			TransactionService.buildTransaction(account, null);
		});
	}
}
