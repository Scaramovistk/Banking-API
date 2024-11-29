package com.capgemini.app.Controller;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.app.Service.AccountService;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

	@GetMapping("/{id}")
	public String getAccount(@PathVariable UUID id) {
		String response = AccountService.getCurrentAccount(id);
		return response;
	}

	@PostMapping("/{id}")
	public String createAccount(@PathVariable UUID id, @RequestBody BigDecimal balance) {
		if (AccountService.createCurrentAccount(id, balance))
			return "Account created successfully!";
		else
			return "Account creation failed!";
	}
}
