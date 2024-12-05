package com.capgemini.app.Controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.capgemini.app.Entity.Account;
import com.capgemini.app.Entity.AccountRequest;
import com.capgemini.app.Entity.Transaction;
import com.capgemini.app.Service.AccountService;
import com.capgemini.app.Service.TransactionService;

@Controller
@RequestMapping("api/v1/accounts/current-accounts")
public class AccountController {

	@GetMapping("/")
	public String createAccountPage() {
		return "index";
	}

	@GetMapping("/display")
	public String displayAccountInfoPage() {
		return "displayInfo";
	}

	@GetMapping("/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable UUID id) {
		Account response = AccountService.getCurrentAccount(id);
		if (response == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> createAccount(@RequestBody AccountRequest accountRequest) {
		UUID id = accountRequest.getId();
		BigDecimal balance = accountRequest.getBalance();

		if (AccountService.buildCurrentAccount(id, BigDecimal.ZERO)) {
			Account account = AccountService.getCurrentAccount(id);
			if (account != null) {
				if (balance.compareTo(BigDecimal.ZERO) > 0) {
					Transaction transaction = TransactionService.buildTransaction(account, balance);
					TransactionService.addTransaction(account, transaction);
				}
				return new ResponseEntity<>("Account created successfully!", HttpStatus.CREATED);
			}
		}
		return new ResponseEntity<>("Account creation failed!", HttpStatus.BAD_REQUEST);
	}
}
