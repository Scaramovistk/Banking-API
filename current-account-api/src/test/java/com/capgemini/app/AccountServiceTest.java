package com.capgemini.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Service.AccountService;

public class AccountServiceTest {

	@Test
	public void createAccountWithInitialBalance() {
		UUID uuid = UUID.randomUUID();
		AccountService.createCurrentAccount(uuid, BigDecimal.valueOf(10));

		Account account = AccountService.getCurrentAccount(uuid);
		assertNotNull(account);
		assertEquals(BigDecimal.valueOf(10.00).setScale(2), account.getBalance());
	}

	@Test
	public void createAccountWithZeroBalance() {
		UUID uuid = UUID.randomUUID();
		AccountService.createCurrentAccount(uuid, BigDecimal.ZERO);

		Account account = AccountService.getCurrentAccount(uuid);
		assertNotNull(account);
		assertEquals(BigDecimal.ZERO.setScale(2), account.getBalance());
	}

	@Test
	public void retrieveNonExistentAccount() {
		UUID uuid = UUID.randomUUID();
		Account account = AccountService.getCurrentAccount(uuid);

		assertNull(account); // Should return null for non-existent accounts
	}

	@Test
	public void multipleAccountsManagement() {
		UUID uuid1 = UUID.randomUUID();
		UUID uuid2 = UUID.randomUUID();

		AccountService.createCurrentAccount(uuid1, BigDecimal.valueOf(100));
		AccountService.createCurrentAccount(uuid2, BigDecimal.valueOf(50));

		Account account1 = AccountService.getCurrentAccount(uuid1);
		Account account2 = AccountService.getCurrentAccount(uuid2);

		assertNotNull(account1);
		assertNotNull(account2);
		assertEquals(BigDecimal.valueOf(100.00).setScale(2), account1.getBalance());
		assertEquals(BigDecimal.valueOf(50.00).setScale(2), account2.getBalance());
	}

	@Test
	public void addTransactionToAccount() {
		UUID uuid = UUID.randomUUID();
		AccountService.createCurrentAccount(uuid, BigDecimal.valueOf(20));

		Account account = AccountService.getCurrentAccount(uuid);
		account.addTransaction(BigDecimal.valueOf(30));

		assertEquals(BigDecimal.valueOf(50.00).setScale(2), account.getBalance());
	}

	@Test
	public void accountWithNegativeTransaction() {
		UUID uuid = UUID.randomUUID();
		AccountService.createCurrentAccount(uuid, BigDecimal.valueOf(50));

		Account account = AccountService.getCurrentAccount(uuid);
		account.addTransaction(BigDecimal.valueOf(-20));

		assertEquals(BigDecimal.valueOf(30.00).setScale(2), account.getBalance());
	}
}
