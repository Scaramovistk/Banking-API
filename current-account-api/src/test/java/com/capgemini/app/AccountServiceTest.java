package com.capgemini.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Service.AccountService;
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
		AccountService.createCurrentAccount(uuid, BigDecimal.valueOf(10));

		Account account = repository.getAccount(uuid);
		assertNotNull(account);
		assertEquals(BigDecimal.valueOf(10.00).setScale(2), account.getBalance());
	}

	@Test
	public void createAccountWithZeroBalance() {
		UUID uuid = UUID.randomUUID();
		AccountService.createCurrentAccount(uuid, BigDecimal.ZERO);

		Account account = repository.getAccount(uuid);
		assertNotNull(account);
		assertEquals(BigDecimal.ZERO.setScale(2), account.getBalance());
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

		AccountService.createCurrentAccount(uuid1, BigDecimal.valueOf(100));
		AccountService.createCurrentAccount(uuid2, BigDecimal.valueOf(50));

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
		AccountService.createCurrentAccount(uuid, BigDecimal.valueOf(20));

		Account account = repository.getAccount(uuid);
		account.addTransaction(BigDecimal.valueOf(30));

		assertEquals(BigDecimal.valueOf(50.00).setScale(2), account.getBalance());
	}

	@Test
	public void accountWithNegativeTransaction() {
		UUID uuid = UUID.randomUUID();
		AccountService.createCurrentAccount(uuid, BigDecimal.valueOf(50));

		Account account = repository.getAccount(uuid);
		account.addTransaction(BigDecimal.valueOf(-20));

		assertEquals(BigDecimal.valueOf(30.00).setScale(2), account.getBalance());
	}

	@Test
	public void createAccountWithInvalidCustomerID() {
		UUID invalidUUID = UUID.randomUUID();
		boolean result = AccountService.createCurrentAccount(invalidUUID, BigDecimal.valueOf(100));

		assertFalse(result);
		Account account = repository.getAccount(invalidUUID);
		assertNull(account);
	}
}
