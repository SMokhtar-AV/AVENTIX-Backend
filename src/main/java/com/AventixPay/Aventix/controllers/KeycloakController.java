package com.AventixPay.Aventix.controllers;

import java.util.List;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.AventixPay.Aventix.service.KeycloakService;

@RestController
@RequestMapping("/users")
@CrossOrigin("*")
public class KeycloakController {

	private final KeycloakService keycloakService;

	public KeycloakController(KeycloakService keycloakService) {
		this.keycloakService = keycloakService;
	}

	@GetMapping()
	public List<UserRepresentation> getAllUsers() {
		return keycloakService.getAllUsers();
	}
}
