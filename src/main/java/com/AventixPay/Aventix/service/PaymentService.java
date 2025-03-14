package com.AventixPay.Aventix.service;


import com.AventixPay.Aventix.XMLFile.PaymentTransactionInfo;
import com.AventixPay.Aventix.XMLFile.XMLGenerator;
import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.entities.Transaction;
import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.enumerated.CardStatut;
import com.AventixPay.Aventix.enumerated.StatutTransaction;
import com.AventixPay.Aventix.repositories.CardRepository;
import com.AventixPay.Aventix.repositories.TransactionRepository;
import com.AventixPay.Aventix.repositories.UserRepository;
import com.AventixPay.Aventix.DTO.PaymentRequest;

//import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/*import javax.xml.bind.annotation.XmlRootElement;*/
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Service

/*@XmlRootElement*/
public class PaymentService {
    @Autowired
    private  CardRepository cardRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private UserRepository userRepository;


    // Gestion Paiement + Génération fichier XML
    public String processPayment(PaymentRequest paymentRequest, Long userId) {

        //Récupérer utilisateur authentifié
     //   User authenticatedUser = getAuthenticatedUser();

        User user = userRepository.findById(userId).get();
        //Vérifier carte virtuelle persistante dans la bd avec serialNumber fourni
        Card card = cardRepository.findByCardNumber(paymentRequest.getCardNumber())
                .orElseThrow(() -> new RuntimeException("Card not found"));

        //Vérifier si la carte est active
        if(card.getStatut() != CardStatut.ACTIVE) {
            throw new RuntimeException("Card is not active");
        }

        //Vérifier si le solde de l'utilisateur est supérieure au montant fourni
        if (card.getUser().getSolde() < paymentRequest.getMontant()) {
            throw new RuntimeException("Montant not enough");
        }

        //Débiter le montant fourni de la carte virtuelle
        card.getUser().setSolde(card.getUser().getSolde() - paymentRequest.getMontant());
        cardRepository.save(card);

        //Ajouter montant du menu au solde du Commercial
        user.setSolde(user.getSolde() + paymentRequest.getMontant());
        userRepository.save(user);

        //Persister une transaction dans la bd
        Transaction transaction = new Transaction();
        transaction.setCard(card);
        transaction.setMontant(paymentRequest.getMontant());
        transaction.setStatutTransaction(StatutTransaction.ENCOURS);
        transaction.setDateTransaction(LocalDateTime.now());
        transaction.setCommercial(user);
        transactionRepository.save(transaction);

        //Objet PaymentTransactionInfo pour XML
        PaymentTransactionInfo paymentInfo = new PaymentTransactionInfo(
                transaction.getId(),
                transaction.getMontant(),
                transaction.getDateTransaction(),
                transaction.getStatutTransaction().toString(),
                card.getCardNumber(),
                card.getValidityDate(),
                card.getStatut().toString(),
                card.getUser().getFirstName(),
                card.getUser().getLastName(),
                user.getId(),
                user.getFirstName(),
                user.getLastName()
        );

        // Générer un fichier XML
        XMLGenerator.generateXMLFile(paymentInfo, "transaction.xml");


        return "Paiement réussi par carte virtuelle de :" + paymentRequest.getMontant() + "$";
    }


    //Récupérer utilisateur connecté
 /*   public User getAuthenticatedUser() {

        //Récupérer objet représentant utilisateur authentifié du contexte de sécurité
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

       //principal instance de KeycloakAuthenticationToken
        if (principal instanceof KeycloakAuthenticationToken) {

            //casting de l'objet principal en un KeycloakAuthenticationToken
            KeycloakAuthenticationToken keycloakToken = (KeycloakAuthenticationToken) principal;
            String email = keycloakToken.getAccount().getKeycloakSecurityContext().getToken().getEmail();

            //Recherche de l'utilisateur sur la bd
            Optional<User> commercial = userRepository.findByEmail(email);
            return commercial.orElse(null);
        }
        return null;
    }*/
}
