package com.AventixPay.Aventix.controllers;


import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.entities.Entreprise;
import com.AventixPay.Aventix.entities.Notification;
import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.enumerated.CardStatut;
import com.AventixPay.Aventix.enumerated.NotificationStatus;
import com.AventixPay.Aventix.repositories.CardRepository;
import com.AventixPay.Aventix.repositories.EntrepriseRepository;
import com.AventixPay.Aventix.repositories.UserRepository;
import com.AventixPay.Aventix.DTO.DeleteCardRequest;
import com.AventixPay.Aventix.DTO.NewCardRequest;
import com.AventixPay.Aventix.DTO.UpdateCardRequest;
import com.AventixPay.Aventix.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.security.authorization.AuthorityReactiveAuthorizationManager.hasRole;

@RestController
@RequestMapping("/api/card")
@CrossOrigin("*")
public class CardController {
    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    @Autowired
    private PaymentService paymentService;


    //Récupérer Liste Entreprises
    @GetMapping("/entreprises")
    public List<Entreprise> findAllEntreprises() {
        return entrepriseRepository.findAll();
    }


    //Récupérer utilisateur par entreprise
    @GetMapping("/entreprises/{entrepriseId}/employees")
    public List<User> getUsersByEntreprise(@PathVariable Long entrepriseId) {
        return userRepository.findByEntrepriseId(entrepriseId);
    }


    //Créer une carte
    @PostMapping("/create")
    public ResponseEntity<Card> createCard(@RequestBody NewCardRequest newCardRequest) {
        Optional<User> user = userRepository.findById(newCardRequest.getUserId());

        Notification notification = new Notification();
        notification.setMessage("Votre carte a été créée avec succès");
        notification.setDateNotification(LocalDate.now());
        notification.setNotificationStatus(NotificationStatus.NONLUE);
        notification.setDestinataire(user.get());

        if(user.isPresent()) {
            Card card = new Card();
            card.setCardNumber(newCardRequest.getCardNumber());
            card.setValidityDate(LocalDate.now().plusYears(3));
            card.setStatut(CardStatut.ACTIVE);
            card.setUser(user.get());
            return ResponseEntity.ok(cardRepository.save(card));
        }
        return ResponseEntity.badRequest().build();
    }


    //Récupérer toutes les cartes
    @GetMapping("/allCards")
    public List<Card> findAllCards() {
        return cardRepository.findAll();
    }


    //Récupérer la liste des cartes par entreprise
    @GetMapping("/{entrepriseId}/all")
    public List<Card> findCardsByEntreprise(@PathVariable Long entrepriseId) {
        return cardRepository.findCardsByEnterpriseId(entrepriseId);
    }


    //Supprimer une carte par id utilisateur
    @DeleteMapping("/delete")
    public ResponseEntity<Card> deleteCard(DeleteCardRequest deleteCardRequest) {
        Long idCard = deleteCardRequest.getCardId();
        return cardRepository.deleteByID(idCard);
    }


    //Activer,Desactiver,Bloquer Carte
    @PostMapping("/updateCardStatut")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateCardStatut(@RequestBody UpdateCardRequest updateCardRequest) {

        //Récupération du propriétaire de la carte virtuelle
        Optional<User> cardOwner = userRepository.findById(updateCardRequest.getIdUser());

        Card card = cardRepository.findByUserId(cardOwner);

        card.setStatut(updateCardRequest.getStatut());

        //Déclenchement d'une notification suite au changement de statut de la carte virtuelle
        Notification notification = new Notification();
        notification.setMessage("Le statut de votre carte a été changé à : "+card.getStatut().toString());
        notification.setDateNotification(LocalDate.now());
        notification.setNotificationStatus(NotificationStatus.NONLUE);
        notification.setDestinataire(cardOwner.get());

        cardRepository.save(card);

        return "Carte Successfully updated";
    }


}
