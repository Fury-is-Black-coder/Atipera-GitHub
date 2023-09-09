package com.example.AtiperaGitHub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AtiperaGitHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(AtiperaGitHubApplication.class, args);
		System.out.println("--- The simple Spring app returns github repositories, its owner, branches and latest commit sha. ---\n" +
				"Use Postman app: http://localhost:8080/repositories/{username}\n"+
				"Example: http://localhost:8080/repositories/Fury-is-Black-coder\n"+
				"Created by Anton Mekh for Atipera");
	}

}
