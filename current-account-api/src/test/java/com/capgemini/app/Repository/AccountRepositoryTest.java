package com.capgemini.app.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Repository.AccountRepository;

public class AccountRepositoryTest {

	private AccountRepository repository;
	private UUID customerID;
	private Account account;

	@BeforeEach
	public void setUp() {
		repository = AccountRepository.getInstance();
		customerID = UUID.randomUUID();
		account = new Account(customerID, "John", "Doe");
	}

	@Test
	public void testSaveAccount() {
		repository.saveAccount(account);
		Account retrievedAccount = repository.getAccount(customerID);
		assertNotNull(retrievedAccount);
		assertEquals(account, retrievedAccount);
	}

	@Test
	public void testGetAccount() {
		repository.saveAccount(account);
		Account retrievedAccount = repository.getAccount(customerID);
		assertNotNull(retrievedAccount);
		assertEquals(account, retrievedAccount);
	}

	@Test
	public void testUpdateAccount() {
		repository.saveAccount(account);
		account.setName("Jane");
		repository.updateAccount(account);
		Account updatedAccount = repository.getAccount(customerID);
		assertNotNull(updatedAccount);
		assertEquals("Jane", updatedAccount.getName());
	}

	@Test
	public void testCheckID() {
		repository.saveAccount(account);
		assertTrue(repository.checkID(customerID));
		assertFalse(repository.checkID(UUID.randomUUID()));
	}

	@Test
	public void testDeleteAccount() {
		repository.saveAccount(account);
		repository.deleteAccount(customerID);
		Account deletedAccount = repository.getAccount(customerID);
		assertNull(deletedAccount);
	}
}
