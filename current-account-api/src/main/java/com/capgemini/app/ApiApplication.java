package com.capgemini.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.capgemini.app.DataFixture.AppFixture;

@SpringBootApplication
public class ApiApplication {
	public static void main(String[] args) {
		AppFixture.loadFixtureData();
		SpringApplication.run(ApiApplication.class, args);
	}
}
