package com.capgemini.app.Accounts;

import java.util.UUID;
import java.math.BigDecimal;

public class CurrentAccount extends AAccount {

	public CurrentAccount(UUID customerID, BigDecimal balance)
	{
		super(customerID, balance);
	}
}
