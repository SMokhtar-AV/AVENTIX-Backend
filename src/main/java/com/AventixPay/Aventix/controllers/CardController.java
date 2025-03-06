package com.AventixPay.Aventix.controllers;


import com.AventixPay.Aventix.entities.Card;
import com.AventixPay.Aventix.entities.Entreprise;
import com.AventixPay.Aventix.entities.Notification;
import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.enumClass.CardStatut;
import com.AventixPay.Aventix.enumClass.NotificationStatus;
import com.AventixPay.Aventix.repositories.CardRepository;
import com.AventixPay.Aventix.repositories.EntrepriseRepository;
import com.AventixPay.Aventix.repositories.UserRepository;
import com.AventixPay.Aventix.request.DeleteCardRequest;
import com.AventixPay.Aventix.request.NewCardRequest;
import com.AventixPay.Aventix.request.UpdateCardRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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
            card.setSolde(1000.0);
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
    public ResponseEntity<Card> updateCardStatut(@RequestBody UpdateCardRequest updateCardRequest) {
        Optional<User> user = userRepository.findById(updateCardRequest.getIdUser());
        Long idCard = user.get().getCard().getId();

    }
}
