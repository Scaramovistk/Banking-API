package com.capgemini.app.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

public class AccountRequestTest {

	private AccountRequest accountRequest = new AccountRequest();
	private UUID customerID = UUID.randomUUID();
	private BigDecimal balance = BigDecimal.valueOf(1000.00);

	@Test
	public void testGetId() {
		accountRequest.setId(customerID);
		assertNotNull(accountRequest.getId());
		assertEquals(customerID, accountRequest.getId());
	}

	@Test
	public void testSetId() {
		UUID newCustomerID = UUID.randomUUID();
		accountRequest.setId(newCustomerID);
		assertEquals(newCustomerID, accountRequest.getId());
	}

	@Test
	public void testGetBalance() {
		accountRequest.setBalance(balance);
		assertNotNull(accountRequest.getBalance());
		assertEquals(balance, accountRequest.getBalance());
	}

	@Test
	public void testSetBalance() {
		BigDecimal newBalance = BigDecimal.valueOf(2000.00);
		accountRequest.setBalance(newBalance);
		assertEquals(newBalance, accountRequest.getBalance());
	}
}
