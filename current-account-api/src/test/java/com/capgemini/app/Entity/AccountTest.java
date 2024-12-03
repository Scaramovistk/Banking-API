package com.capgemini.app.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Entity.Transaction;

public class AccountTest {

	@Test
	public void balanceTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");
		assertEquals(BigDecimal.valueOf(0.00).setScale(2), account.getBalance());
	}

	@Test
	public void oneTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");
		Transaction transaction = new Transaction("1", BigDecimal.valueOf(10));
		account.addTransaction(transaction);
		assertEquals(BigDecimal.valueOf(10.00).setScale(2), account.getBalance());
	}

	@Test
	public void negativeTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");
		Transaction transaction1 = new Transaction("1", BigDecimal.valueOf(10));
		Transaction transaction2 = new Transaction("2", BigDecimal.valueOf(-5));
		account.addTransaction(transaction1);
		account.addTransaction(transaction2);
		assertEquals(BigDecimal.valueOf(5.00).setScale(2), account.getBalance());
	}

	@Test
	public void floatPointTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");
		Transaction transaction1 = new Transaction("1", BigDecimal.valueOf(10));
		Transaction transaction2 = new Transaction("2", BigDecimal.valueOf(-5.5));
		account.addTransaction(transaction1);
		account.addTransaction(transaction2);
		assertEquals(BigDecimal.valueOf(4.50).setScale(2), account.getBalance());
	}

	@Test
	public void multipleTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");

		Transaction transaction1 = new Transaction("1", BigDecimal.valueOf(10));
		Transaction transaction2 = new Transaction("2", BigDecimal.valueOf(-5.5));
		Transaction transaction3 = new Transaction("3", BigDecimal.valueOf(20));
		Transaction transaction4 = new Transaction("4", BigDecimal.valueOf(-3.5));

		account.addTransaction(transaction1);
		account.addTransaction(transaction2);
		account.addTransaction(transaction3);
		account.addTransaction(transaction4);

		assertEquals(BigDecimal.valueOf(21.00).setScale(2), account.getBalance());
	}

	@Test
	public void nullTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");
		assertThrows(IllegalArgumentException.class, () -> {
			account.addTransaction(null);
		});
	}

	@Test
	public void truncatedTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");

		Transaction transaction1 = new Transaction("1", BigDecimal.valueOf(10));
		Transaction transaction2 = new Transaction("2", BigDecimal.valueOf(-5.5));
		Transaction transaction3 = new Transaction("3", BigDecimal.valueOf(55.587));
		Transaction transaction4 = new Transaction("4", BigDecimal.valueOf(785.74));

		account.addTransaction(transaction1);
		account.addTransaction(transaction2);
		account.addTransaction(transaction3);
		account.addTransaction(transaction4);

		assertEquals(BigDecimal.valueOf(845.83).setScale(2), account.getBalance());
	}

	@Test
	public void largeValueTransactionTest() {
		Account account = new Account(UUID.randomUUID(), "Bruce", "Lee");

		Transaction transaction1 = new Transaction("1", BigDecimal.valueOf(1_000_000_000));
		Transaction transaction2 = new Transaction("2", BigDecimal.valueOf(-999_999_999.99));

		account.addTransaction(transaction1);
		account.addTransaction(transaction2);

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

		Transaction transaction1 = new Transaction("1", BigDecimal.valueOf(10));
		Transaction transaction2 = new Transaction("2", BigDecimal.valueOf(-5.5));
		Transaction transaction3 = new Transaction("3", BigDecimal.valueOf(20));
		Transaction transaction4 = new Transaction("4", BigDecimal.valueOf(-3.5));

		account.addTransaction(transaction1);
		account.addTransaction(transaction2);
		account.addTransaction(transaction3);
		account.addTransaction(transaction4);

		assertEquals(BigDecimal.valueOf(21.00).setScale(2), account.getBalance());
		assertEquals(4, account.getLedger().size());
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

		Transaction transaction1 = new Transaction("1", BigDecimal.valueOf(50.25));
		Transaction transaction2 = new Transaction("2", BigDecimal.valueOf(-20.10));
		account.addTransaction(transaction1);
		account.addTransaction(transaction2);

		List<Transaction> newLedger = List.of(
			new Transaction("3", BigDecimal.valueOf(100)),
			new Transaction("4", BigDecimal.valueOf(-50))
		);
		account.setLedger(newLedger);

		assertEquals(BigDecimal.valueOf(50.00).setScale(2), account.getBalance());
	}
}
