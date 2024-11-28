package com.capgemini.app;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import com.capgemini.app.Accounts.AAccount;
import java.math.BigDecimal;
import java.util.UUID;

public class LedgerTest {

	@Test
	public void balanceTest() {
		AAccount account = new AAccount(UUID.randomUUID(), BigDecimal.valueOf(0));

		assertEquals(0, account.getBalance());
	}

	@Test
	public void initialBalanceTest() {
		AAccount account = new AAccount(UUID.randomUUID(), BigDecimal.valueOf(10.5));

		assertEquals(10.50, account.getBalance());
	}

	@Test
	public void oneTransactionTest() {
		AAccount account = new AAccount(UUID.randomUUID(), BigDecimal.valueOf(0));
		
		account.addTransaction(BigDecimal.valueOf(10));
		assertEquals(10.0, account.getBalance());
	}

	@Test
	public void negativeTransactionTest() {
		AAccount account = new AAccount(UUID.randomUUID(), BigDecimal.valueOf(0));
		
		account.addTransaction(BigDecimal.valueOf(10));
		account.addTransaction(BigDecimal.valueOf(-5));
		assertEquals(5.0, account.getBalance());
	}

	@Test
	public void floatPointTransactionTest() {
		AAccount account = new AAccount(UUID.randomUUID(), BigDecimal.valueOf(0));
		
		account.addTransaction(BigDecimal.valueOf(10));
		account.addTransaction(BigDecimal.valueOf(-5.5));
		assertEquals(4.5, account.getBalance());
	}

	@Test
	public void multipleTransactionTest() {
		AAccount account = new AAccount(UUID.randomUUID(), BigDecimal.valueOf(0));

		account.addTransaction(BigDecimal.valueOf(10));
		account.addTransaction(BigDecimal.valueOf(-5.5));
		account.addTransaction(BigDecimal.valueOf(-0));
		account.addTransaction(BigDecimal.valueOf(18489789156456457975479.4578547554787557));
		account.addTransaction(BigDecimal.valueOf(-18489789156456457975479.4578547554787557));
		account.addTransaction(BigDecimal.valueOf(000000000000.00000000));
		account.addTransaction(BigDecimal.valueOf(55.58));
		account.addTransaction(BigDecimal.valueOf(785.74));
		assertEquals(845.82, account.getBalance());
	}

	@Test
	public void truncatedTransactionTest() {
		AAccount account = new AAccount(UUID.randomUUID(), BigDecimal.valueOf(0));

		account.addTransaction(BigDecimal.valueOf(10));
		account.addTransaction(BigDecimal.valueOf(-5.5));
		account.addTransaction(BigDecimal.valueOf(55.587));
		account.addTransaction(BigDecimal.valueOf(785.74));
		assertEquals(845.83, account.getBalance());
	}
}
