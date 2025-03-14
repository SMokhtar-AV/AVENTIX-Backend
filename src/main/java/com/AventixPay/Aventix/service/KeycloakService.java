package com.AventixPay.Aventix.service;

import java.util.List;

import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakService {

	List<UserRepresentation> getAllUsers();
}
