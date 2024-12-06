package com.capgemini.app.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.capgemini.app.Abstract.Account;
import com.capgemini.app.Entity.CurrentAccount;

public class AccountRepositoryTest {

	private AccountRepository repository = AccountRepository.getInstance();
	private UUID customerID = UUID.randomUUID();
	private Account account = new CurrentAccount(customerID, "John", "Doe");

	@Test
	public void testSaveAccount() {
		repository.saveCurrentAccount(account);
		Account retrievedAccount = repository.getCurrentAccount(customerID);
		assertNotNull(retrievedAccount);
		assertEquals(account, retrievedAccount);
	}

	@Test
	public void testGetAccount() {
		repository.saveCurrentAccount(account);
		Account retrievedAccount = repository.getCurrentAccount(customerID);
		assertNotNull(retrievedAccount);
		assertEquals(account, retrievedAccount);
	}

	@Test
	public void testUpdateAccount() {
		repository.saveCurrentAccount(account);
		account.setName("Jane");
		repository.updateCurrentAccount(account);
		Account updatedAccount = repository.getCurrentAccount(customerID);
		assertNotNull(updatedAccount);
		assertEquals("Jane", updatedAccount.getName());
	}

	@Test
	public void testcheckCurrentAccountId() {
		repository.saveCurrentAccount(account);
		assertTrue(repository.checkCurrentAccountId(customerID));
		assertFalse(repository.checkCurrentAccountId(UUID.randomUUID()));
	}

	@Test
	public void testDeleteAccount() {
		repository.saveCurrentAccount(account);
		repository.deleteCurrentAccount(customerID);
		Account deletedAccount = repository.getCurrentAccount(customerID);
		assertNull(deletedAccount);
	}
}
