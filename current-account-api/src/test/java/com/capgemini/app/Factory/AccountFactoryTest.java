package com.capgemini.app.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.capgemini.app.Abstract.Account;
import com.capgemini.app.Entity.CurrentAccount;
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
		Account account = CurrentAccountFactory.createAccount(customerID, "Bruce", "Lee");
		assertNotNull(account);
		assertEquals(customerID, account.getCustomerID());
		assertEquals("Bruce", account.getName());
		assertEquals("Lee", account.getSurname());
	}

	@Test
	public void createAccountWithExistingCustomerIDTest() {
		UUID customerID = UUID.randomUUID();
		Account existingAccount = new CurrentAccount(customerID, "Existing", "User");
		repository.saveCurrentAccount(existingAccount);

		Account account = CurrentAccountFactory.createAccount(customerID, "Bruce", "Lee");
		assertNull(account);
	}

	@Test
	public void createAccountWithNullCustomerIDTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			CurrentAccountFactory.createAccount(null, "Bruce", "Lee");
		});
	}

	@Test
	public void createAccountWithEmptyNameTest() {
		UUID customerID = UUID.randomUUID();
		Account account = CurrentAccountFactory.createAccount(customerID, "", "Lee");
		assertNotNull(account);
		assertEquals(customerID, account.getCustomerID());
		assertEquals("", account.getName());
		assertEquals("Lee", account.getSurname());
	}

	@Test
	public void createAccountWithEmptySurnameTest() {
		UUID customerID = UUID.randomUUID();
		Account account = CurrentAccountFactory.createAccount(customerID, "Bruce", "");
		assertNotNull(account);
		assertEquals(customerID, account.getCustomerID());
		assertEquals("Bruce", account.getName());
		assertEquals("", account.getSurname());
	}

	@Test
	public void createAccountWithNullNameTest() {
		UUID customerID = UUID.randomUUID();
		assertThrows(IllegalArgumentException.class, () -> {
			CurrentAccountFactory.createAccount(customerID, null, "Lee");
		});
	}

	@Test
	public void createAccountWithNullSurnameTest() {
		UUID customerID = UUID.randomUUID();
		assertThrows(IllegalArgumentException.class, () -> {
			CurrentAccountFactory.createAccount(customerID, "Bruce", null);
		});
	}
}
