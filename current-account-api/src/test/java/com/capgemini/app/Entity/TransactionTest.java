package com.capgemini.app.Entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.capgemini.app.Entity.Transaction;

public class TransactionTest {

	@Test
	public void createTransactionWithValidDataTest() {
		Transaction transaction = new Transaction("1", BigDecimal.valueOf(100));
		assertNotNull(transaction);
		assertEquals("1", transaction.getTransactionID());
		assertEquals(BigDecimal.valueOf(100), transaction.getAmount());
	}

	@Test
	public void createTransactionWithNullIDTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Transaction(null, BigDecimal.valueOf(100));
		});
	}

	@Test
	public void createTransactionWithNullAmountTest() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Transaction("1", null);
		});
	}

	@Test
	public void setTransactionIDTest() {
		Transaction transaction = new Transaction("1", BigDecimal.valueOf(100));
		transaction.setTransactionID("2");
		assertEquals("2", transaction.getTransactionID());
	}

	@Test
	public void setAmountTest() {
		Transaction transaction = new Transaction("1", BigDecimal.valueOf(100));
		transaction.setAmount(BigDecimal.valueOf(200));
		assertEquals(BigDecimal.valueOf(200), transaction.getAmount());
	}

	@Test
	public void createTransactionWithNegativeAmountTest() {
		Transaction transaction = new Transaction("1", BigDecimal.valueOf(-100));
		assertNotNull(transaction);
		assertEquals("1", transaction.getTransactionID());
		assertEquals(BigDecimal.valueOf(-100), transaction.getAmount());
	}

	@Test
	public void createTransactionWithZeroAmountTest() {
		Transaction transaction = new Transaction("1", BigDecimal.valueOf(0));
		assertNotNull(transaction);
		assertEquals("1", transaction.getTransactionID());
		assertEquals(BigDecimal.valueOf(0), transaction.getAmount());
	}

	@Test
	public void defaultConstructorTest() {
		Transaction transaction = new Transaction();
		assertNotNull(transaction);
		assertEquals("", transaction.getTransactionID());
		assertEquals(BigDecimal.valueOf(0), transaction.getAmount());
	}
}
