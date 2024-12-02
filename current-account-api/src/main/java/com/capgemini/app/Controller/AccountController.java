package com.capgemini.app.Controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Entity.Transaction;
import com.capgemini.app.Service.AccountService;
import com.capgemini.app.Service.TransactionService;

@RestController
@RequestMapping("/api/v1/accounts/current-accounts")
public class AccountController {

	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable UUID id) {
		Account response = AccountService.getCurrentAccount(id);
		if (response == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping("/{id}")
	public ResponseEntity<String> createAccount(@PathVariable UUID id) {
		if (AccountService.buildCurrentAccount(id, BigDecimal.ZERO)) {
			Account account = AccountService.getCurrentAccount(id);
			if (account != null) {
				Transaction transaction1 = TransactionService.buildTransaction(account, BigDecimal.valueOf(10));
				Transaction transaction2 = TransactionService.buildTransaction(account, BigDecimal.valueOf(15));
				Transaction transaction3 = TransactionService.buildTransaction(account, BigDecimal.valueOf(178.118));

				TransactionService.addTransaction(account, transaction1);
				TransactionService.addTransaction(account, transaction2);
				TransactionService.addTransaction(account, transaction3);

				return new ResponseEntity<>("Account created successfully!", HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>("Account creation failed!", HttpStatus.BAD_REQUEST);
	}
}
