package com.AventixPay.Aventix.service.serviceImpl;

import java.security.SecureRandom;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
 import com.AventixPay.Aventix.entities.User;
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

    @Value("${spring.mail.password}")
    private String sendGridApiKey;

    @PostConstruct
    public void init() {
        keycloak = KeycloakBuilder.builder()
                .serverUrl(keycloakServerUrl)
                .realm("master") // Always use "master" for admin access
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

    public String createUser(User userDTO) {
        // Génération du mot de passe aléatoire
        String generatedPassword = generateRandomPassword(12); // 12 caractères, ajustable

        // Création de l'utilisateur dans Keycloak
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        
        user.setEnabled(true);
        user.setCredentials(Collections.singletonList(createPasswordCredentials(generatedPassword)));

        UsersResource usersResource = keycloak.realm(realm).users();
        javax.ws.rs.core.Response response = usersResource.create(user);

        if (response.getStatus() == 201) {
            // Envoi de l'email de confirmation via SendGrid
          //   sendEmail(user.getEmail(), "Création de votre compte", "Votre mot de passe temporaire est : " + generatedPassword);
            return "Utilisateur créé avec succès dans Keycloak et email envoyé.";
        } else {
            return "Erreur lors de la création de l'utilisateur Keycloak.";
        }
    }

    private CredentialRepresentation createPasswordCredentials(String password) {
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(password);
        return passwordCred;
    }

    private String generateRandomPassword(int length) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_-+=<>?";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            password.append(CHARACTERS.charAt(randomIndex));
        }

        return password.toString();
    }

    private void sendEmail(String toEmail, String subject, String body) {
        try {
            // Utilisation de SendGrid pour envoyer un email
            Email from = new Email("aziiz.ahmeed@gmail.com"); // L'email vérifié dans SendGrid
            Email to = new Email(toEmail); // Adresse email du destinataire
            Content content = new Content("text/plain", body); // Corps de l'email
            Mail mail = new Mail(from, subject, to, content);

            SendGrid sg = new SendGrid(sendGridApiKey); // Clé API SendGrid
            Request request = new Request();
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            com.sendgrid.Response response = sg.api(request); // Envoi de l'email

            LOGGER.info("Email envoyé avec succès : " + response.getStatusCode());
        } catch (Exception e) {
            LOGGER.severe("Erreur lors de l'envoi de l'email : " + e.getMessage());
        }
    }

	 
}
