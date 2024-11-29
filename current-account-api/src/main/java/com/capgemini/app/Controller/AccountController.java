package com.capgemini.app.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/accounts")
public class AccountController {

	@GetMapping("/status")
	public String getStatus() {
		return "Account service is up and running!";
	}
}