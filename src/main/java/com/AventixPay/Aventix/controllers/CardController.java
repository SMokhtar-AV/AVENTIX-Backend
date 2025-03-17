package com.AventixPay.Aventix.controllers;


import com.AventixPay.Aventix.entities.*;
import com.AventixPay.Aventix.enumClass.DemandeEtat;
import com.AventixPay.Aventix.enumClass.Role;
import com.AventixPay.Aventix.enumerated.CardStatut;
import com.AventixPay.Aventix.enumerated.NotificationStatus;
import com.AventixPay.Aventix.repositories.*;
import com.AventixPay.Aventix.DTO.DeleteCardRequest;
import com.AventixPay.Aventix.DTO.NewCardRequest;
import com.AventixPay.Aventix.DTO.UpdateCardRequest;
import com.AventixPay.Aventix.service.PaymentService;
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

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DemandRepository demandRepository;
    @Autowired
    private NotificationRepository notificationRepository;
    @Autowired
    private RoleRepository roleRepository;

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
        Optional<User> user = userRepository.findByEmail(newCardRequest.getEmail());
        User user2 = user.get();

        Roles role = roleRepository.findByRole(Role.ADMIN).get();
        List<User> listUsers = user2.getEntreprise().getListUser();
        User userEmpl = listUsers.stream().filter(roleUser -> roleUser.getRole().equals(role)).findFirst().get();


        Demand demand = demandRepository.findById(newCardRequest.getDemandeId()).get();
        demand.setEtat(DemandeEtat.VALIDE);
        demandRepository.save(demand);
        Notification notification = new Notification();
        notification.setDateNotification(LocalDate.now());
        notification.setNotificationStatus(NotificationStatus.NONLUE);
        notification.setMessage("La carte de votre employe "+ user2.getFirstName() + " " + user2.getLastName()+ " est cree");
        notification.setDestinataire(userEmpl);
        notificationRepository.save(notification);
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

/*
    //Récupérer la liste des cartes par entreprise
    @GetMapping("/{entrepriseId}/all")
    public List<Card> findCardsByEntreprise(@PathVariable Long entrepriseId) {
        return cardRepository.findCardsByEnterpriseId(entrepriseId);
    }*/


    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteCard(DeleteCardRequest deleteCardRequest) {
        Long idCard = deleteCardRequest.getCardId();
        cardRepository.deleteById(idCard);  // Use deleteById instead of deleteByID
        return ResponseEntity.ok().build();  // Return OK response after deletion
    }


    //Activer,Desactiver,Bloquer Carte
    @PostMapping("/updateCardStatut")
    //@PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String updateCardStatut(@RequestBody UpdateCardRequest updateCardRequest) {

        //Récupération du propriétaire de la carte virtuelle
        Optional<User> cardOwner = userRepository.findById(updateCardRequest.getIdUser());

        Card card = cardRepository.findByUser(cardOwner);

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
