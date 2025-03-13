package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumerated.CardStatut;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateCreation;

    private LocalDate dateExpiration;

    private String entreprise;

    private String nomEmploye;

    private Double soldeInitial;

    private CardStatut cardStatut = CardStatut.ACTIVE;

    private double montantTotalFacture;

    @OneToOne
    private Transaction transaction;



    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



    public User getUser() {
        return user;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public double getMontantTotalFacture() {
        return montantTotalFacture;
    }

    public CardStatut getCardStatut() {
        return cardStatut;
    }

    public Double getSoldeInitial() {
        return soldeInitial;
    }

    public String getNomEmploye() {
        return nomEmploye;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public LocalDate getDateExpiration() {
        return dateExpiration;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setDateExpiration(LocalDate dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public void setNomEmploye(String nomEmploye) {
        this.nomEmploye = nomEmploye;
    }

    public void setSoldeInitial(Double soldeInitial) {
        this.soldeInitial = soldeInitial;
    }

    public void setCardStatut(CardStatut cardStatut) {
        this.cardStatut = cardStatut;
    }

    public void setMontantTotalFacture(double montantTotalFacture) {
        this.montantTotalFacture = montantTotalFacture;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
