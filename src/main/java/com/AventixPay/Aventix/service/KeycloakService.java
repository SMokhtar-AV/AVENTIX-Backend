package com.AventixPay.Aventix.service;

import java.util.List;

import com.AventixPay.Aventix.entities.User;
import org.keycloak.representations.idm.UserRepresentation;

public interface KeycloakService {

	List<UserRepresentation> getAllUsers();

	String createUser(User user);
}
