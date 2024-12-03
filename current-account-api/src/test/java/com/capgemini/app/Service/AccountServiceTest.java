package com.capgemini.app.Service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Entity.Transaction;
import com.capgemini.app.Service.AccountService;
import com.capgemini.app.Service.TransactionService;
import com.capgemini.app.Repository.AccountRepository;

public class AccountServiceTest {

	private AccountRepository repository;

	@BeforeEach
	public void setUp() {
		repository = AccountRepository.getInstance();
	}

	@Test
	public void createAccountWithInitialBalance() {
		UUID uuid = UUID.randomUUID();
		boolean result = AccountService.buildCurrentAccount(uuid, BigDecimal.valueOf(10));
		assertTrue(result);

		Account account = repository.getAccount(uuid);
		assertNotNull(account);
		assertEquals(BigDecimal.valueOf(10.00).setScale(2), account.getBalance());
	}

	@Test
	public void createAccountWithZeroBalance() {
		UUID uuid = UUID.randomUUID();
		boolean result = AccountService.buildCurrentAccount(uuid, BigDecimal.ZERO);
		assertTrue(result);

		Account account = repository.getAccount(uuid);
		assertNotNull(account);
		assertEquals(BigDecimal.ZERO.setScale(2), account.getBalance());
	}

	@Test
	public void createAccountWithNegativeBalance() {
		UUID uuid = UUID.randomUUID();
		assertThrows(IllegalArgumentException.class, () -> {
			AccountService.buildCurrentAccount(uuid, BigDecimal.valueOf(-10));
		});
	}

	@Test
	public void retrieveNonExistentAccount() {
		UUID uuid = UUID.randomUUID();
		Account account = repository.getAccount(uuid);

		assertNull(account);
	}

	@Test
	public void multipleAccountsManagement() {
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();

		boolean result1 = AccountService.buildCurrentAccount(uuid1, BigDecimal.valueOf(100));
		boolean result2 = AccountService.buildCurrentAccount(uuid2, BigDecimal.valueOf(50));
		assertTrue(result1);
		assertTrue(result2);

		Account account1 = repository.getAccount(uuid1);
		Account account2 = repository.getAccount(uuid2);

		assertNotNull(account1);
		assertNotNull(account2);
		assertEquals(BigDecimal.valueOf(100.00).setScale(2), account1.getBalance());
		assertEquals(BigDecimal.valueOf(50.00).setScale(2), account2.getBalance());
	}

	@Test
	public void addTransactionToAccount() {
		UUID uuid = UUID.randomUUID();
		boolean result = AccountService.buildCurrentAccount(uuid, BigDecimal.valueOf(20));
		assertTrue(result);

		Account account = repository.getAccount(uuid);
		Transaction transaction = TransactionService.buildTransaction(account, BigDecimal.valueOf(30));
		TransactionService.addTransaction(account, transaction);

		assertEquals(BigDecimal.valueOf(50.00).setScale(2), account.getBalance());
	}
}
