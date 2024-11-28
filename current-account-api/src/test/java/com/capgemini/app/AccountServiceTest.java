package com.capgemini.app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.capgemini.app.Accounts.AAccount;
import com.capgemini.app.Accounts.AccountService;

public class AccountServiceTest {
    
    @Test
	public void accountServices() {
		UUID uuid = UUID.randomUUID();
		AccountService.createCurrentAccount(uuid, BigDecimal.valueOf(10));
		AAccount account = AccountService.getCurrentAccount(uuid);
	
		assertEquals(10, account.getBalance());
	}
}
