package com.capgemini.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.capgemini.app.Entity.Account;

public class AccountTest {

	@Test
	public void balanceTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");
		assertEquals(BigDecimal.valueOf(0.00).setScale(2), account.getBalance());
	}

	@Test
	public void oneTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");
		account.addTransaction(BigDecimal.valueOf(10));
		assertEquals(BigDecimal.valueOf(10.00).setScale(2), account.getBalance());
	}

	@Test
	public void negativeTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");
		account.addTransaction(BigDecimal.valueOf(10));
		account.addTransaction(BigDecimal.valueOf(-5));
		assertEquals(BigDecimal.valueOf(5.00).setScale(2), account.getBalance());
	}

	@Test
	public void floatPointTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");
		account.addTransaction(BigDecimal.valueOf(10));
		account.addTransaction(BigDecimal.valueOf(-5.5));
		assertEquals(BigDecimal.valueOf(4.50).setScale(2), account.getBalance());
	}

	@Test
	public void multipleTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");

		account.addTransaction(BigDecimal.valueOf(10));
		account.addTransaction(BigDecimal.valueOf(-5.5));
		account.addTransaction(BigDecimal.ZERO);
		account.addTransaction(BigDecimal.valueOf(184897.45));
		account.addTransaction(BigDecimal.valueOf(-184897.45));
		account.addTransaction(BigDecimal.ZERO);
		account.addTransaction(BigDecimal.valueOf(55.58));
		account.addTransaction(BigDecimal.valueOf(785.74));
		assertEquals(BigDecimal.valueOf(845.82).setScale(2), account.getBalance());
	}

	@Test
	public void truncatedTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");

		account.addTransaction(BigDecimal.valueOf(10));
		account.addTransaction(BigDecimal.valueOf(-5.5));
		account.addTransaction(BigDecimal.valueOf(55.587)); // Should truncate to 55.59
		account.addTransaction(BigDecimal.valueOf(785.74));
		assertEquals(BigDecimal.valueOf(845.83).setScale(2), account.getBalance());
	}

	@Test
	public void largeValueTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");

		account.addTransaction(BigDecimal.valueOf(1_000_000_000));
		account.addTransaction(BigDecimal.valueOf(-999_999_999.99));
		assertEquals(BigDecimal.valueOf(0.01).setScale(2), account.getBalance());
	}

	@Test
	public void noTransactionsTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");
		assertEquals(BigDecimal.valueOf(0.00).setScale(2), account.getBalance());
	}

	@Test
	public void emptyLedgerBalanceTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");

		account.setLedger(new ArrayList<>());
		assertEquals(BigDecimal.valueOf(0.00).setScale(2), account.getBalance());
	}

	@Test
	public void ledgerIntegrityTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");

		account.addTransaction(BigDecimal.valueOf(50.25));
		account.addTransaction(BigDecimal.valueOf(-20.10));
		account.addTransaction(BigDecimal.valueOf(30));
		List<BigDecimal> expectedLedger = List.of(
			BigDecimal.valueOf(50.25).setScale(2),
			BigDecimal.valueOf(-20.1),
			BigDecimal.valueOf(30)
		);
		assertEquals(expectedLedger, account.getLedger());
	}

	@Test
	public void invalidTransactionTest() {
	Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");

	Exception exception = assertThrows(IllegalArgumentException.class, () -> {
		account.addTransaction(null);
	});

	assertEquals("Transaction cannot be null", exception.getMessage());
}


	@Test
	public void updateBalanceAfterLedgerModificationTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");

		account.addTransaction(BigDecimal.valueOf(50.25));
		account.addTransaction(BigDecimal.valueOf(-20.10));
		account.setLedger(List.of(BigDecimal.valueOf(100), BigDecimal.valueOf(-50)));
		assertEquals(BigDecimal.valueOf(50.00).setScale(2), account.getBalance());
	}
}
