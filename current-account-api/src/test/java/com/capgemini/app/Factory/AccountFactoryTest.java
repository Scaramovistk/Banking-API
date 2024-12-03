package com.capgemini.app.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Factory.AccountFactory;
import com.capgemini.app.Repository.AccountRepository;

public class AccountFactoryTest {

	private AccountRepository repository;

	@BeforeEach
	public void setUp() {
		repository = AccountRepository.getInstance();
	}

	@Test
	public void createAccountWithValidDataTest() {
		UUID customerID = UUID.randomUUID();
		Account account = AccountFactory.createAccount(customerID, "Bruce", "Lee");
		assertNotNull(account);
		assertEquals(customerID, account.getCustomerID());
		assertEquals("Bruce", account.getName());
		assertEquals("Lee", account.getSurname());
	}

	@Test
	public void createAccountWithExistingCustomerIDTest() {
		UUID customerID = UUID.randomUUID();
		Account existingAccount = new Account(customerID, "Existing", "User");
		repository.saveAccount(existingAccount);

		Account account = AccountFactory.createAccount(customerID, "Bruce", "Lee");
		assertNull(account); // Account creation should fail because the customer ID already exists
	}

	@Test
	public void createAccountWithNullCustomerIDTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			AccountFactory.createAccount(null, "Bruce", "Lee");
		});
	}

	@Test
	public void createAccountWithEmptyNameTest() {
		UUID customerID = UUID.randomUUID();
		Account account = AccountFactory.createAccount(customerID, "", "Lee");
		assertNotNull(account);
		assertEquals(customerID, account.getCustomerID());
		assertEquals("", account.getName());
		assertEquals("Lee", account.getSurname());
	}

	@Test
	public void createAccountWithEmptySurnameTest() {
		UUID customerID = UUID.randomUUID();
		Account account = AccountFactory.createAccount(customerID, "Bruce", "");
		assertNotNull(account);
		assertEquals(customerID, account.getCustomerID());
		assertEquals("Bruce", account.getName());
		assertEquals("", account.getSurname());
	}

	@Test
	public void createAccountWithNullNameTest() {
		UUID customerID = UUID.randomUUID();
		assertThrows(IllegalArgumentException.class, () -> {
			AccountFactory.createAccount(customerID, null, "Lee");
		});
	}

	@Test
	public void createAccountWithNullSurnameTest() {
		UUID customerID = UUID.randomUUID();
		assertThrows(IllegalArgumentException.class, () -> {
			AccountFactory.createAccount(customerID, "Bruce", null);
		});
	}
}
