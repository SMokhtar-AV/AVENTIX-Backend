package com.AventixPay.Aventix.service.serviceImpl;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ws.rs.core.Response;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.AventixPay.Aventix.service.KeycloakService;

@Service
public class KeycloakServiceImpl implements KeycloakService {
	private static final Logger LOGGER = Logger.getLogger(KeycloakServiceImpl.class.getName());

	@Value("${keycloak.auth-server-url}")
	private String keycloakServerUrl;

	@Value("${keycloak.realm}")
	private String realm;

	@Value("${keycloak.resource}")
	private String clientId;

	@Value("${keycloak.credentials.secret}")
	private String clientSecret;

	private Keycloak keycloak;

	@PostConstruct
	public void init() {
		keycloak = KeycloakBuilder.builder().serverUrl(keycloakServerUrl).realm("master") // Always use "master" for
																							// admin access
				.clientId("admin-cli") // Required for admin actions
				.username("admin") // Keycloak admin username
				.password("root") // Keycloak admin password
				.build();
	}

	public List<UserRepresentation> getAllUsers() {
		LOGGER.info("Fetching all users from Keycloak...");
		List<UserRepresentation> users = keycloak.realm(realm).users().list();
		LOGGER.info("Number of users found: " + users.size());
		return users;
	}

	public String createUser(String username, String email, String password) {
		UserRepresentation user = new UserRepresentation();
		user.setUsername(username);
		user.setEmail(email);
		user.setEnabled(true);
		user.setCredentials(Collections.singletonList(createPasswordCredentials(password)));

		UsersResource usersResource = keycloak.realm(realm).users();
		Response response = usersResource.create(user);

		if (response.getStatus() == 201) {
			return "Utilisateur créé avec succès dans Keycloak";
		} else {
			throw new RuntimeException("Erreur lors de la création de l'utilisateur Keycloak");
		}
	}

	private CredentialRepresentation createPasswordCredentials(String password) {
		CredentialRepresentation passwordCred = new CredentialRepresentation();
		passwordCred.setTemporary(false);
		passwordCred.setType(CredentialRepresentation.PASSWORD);
		passwordCred.setValue(password);
		return passwordCred;
	}
}
