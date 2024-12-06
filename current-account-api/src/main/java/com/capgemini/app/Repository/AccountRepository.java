package com.capgemini.app.Repository;

import java.util.HashMap;
import java.util.UUID;

import com.capgemini.app.Abstract.Account;

public class AccountRepository {

	static private AccountRepository _instance;
	static private HashMap<UUID, Account> _accounts = new HashMap<UUID, Account>();
	static private HashMap<UUID, Account> _current_accounts = new HashMap<UUID, Account>();

	private AccountRepository() {
	}

	public static AccountRepository getInstance() {
		if (_instance == null)
			_instance = new AccountRepository();
		return (_instance);
	}

	public Account getAccount(UUID accountID) {
		return _accounts.get(accountID);
	}

	public void saveAccount(Account account) {
		_accounts.put(account.getCustomerID(), account);
	}

	public void updateAccount(Account account) {
		_accounts.put(account.getCustomerID(), account);
	}

	public void deleteAccount(UUID accountID) {
		_accounts.remove(accountID);
	}

	public boolean checkAccountID(UUID accountID) {
		return _accounts.containsKey(accountID);
	}

	public Account getCurrentAccount(UUID customerID) {
		return _current_accounts.get(customerID);
	}

	public void saveCurrentAccount(Account account) {
		_current_accounts.put(account.getCustomerID(), account);
	}

	public void updateCurrentAccount(Account account) {
		_current_accounts.put(account.getCustomerID(), account);
	}

	public void deleteCurrentAccount(UUID customerID) {
		_current_accounts.remove(customerID);
	}

	public boolean checkCurrentAccountId(UUID customerID) {
		return _current_accounts.containsKey(customerID);
	}
}
