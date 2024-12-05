package com.capgemini.app.Factory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.capgemini.app.Entity.Transaction;

public class TransactionFactoryTest {

	@Test
	public void testCreateTransaction() {
		UUID accountId = UUID.randomUUID();
		BigDecimal amount = BigDecimal.valueOf(100.00);

		Transaction transaction = TransactionFactory.createTransaction(accountId, amount);

		assertNotNull(transaction);
		assertNotNull(transaction.getTransactionID());
		assertEquals(amount, transaction.getAmount());
	}

	@Test
	public void testCreateTransactionWithNullAccountId() {
		BigDecimal amount = BigDecimal.valueOf(100.00);

		assertThrows(IllegalArgumentException.class, () -> {
			TransactionFactory.createTransaction(null, amount);
		});
	}

	@Test
	public void testCreateTransactionWithNullAmount() {
		UUID accountId = UUID.randomUUID();

		assertThrows(IllegalArgumentException.class, () -> {
			TransactionFactory.createTransaction(accountId, null);
		});
	}
}