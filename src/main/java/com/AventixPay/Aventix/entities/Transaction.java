package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumerated.StatutTransaction;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;


import java.time.LocalDateTime;


@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;

    private LocalDateTime dateTransaction;

    private StatutTransaction statutTransaction;

    @ManyToOne
    @JsonBackReference
    private User commercial;

    @ManyToOne
    @JsonBackReference
    private Card card;

    public void setId(Long id) {
        this.id = id;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setDateTransaction(LocalDateTime dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public void setStatutTransaction(StatutTransaction statutTransaction) {
        this.statutTransaction = statutTransaction;
    }

    public void setCommercial(User commercial) {
        this.commercial = commercial;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public Long getId() {
        return id;
    }

    public double getMontant() {
        return montant;
    }

    public LocalDateTime getDateTransaction() {
        return dateTransaction;
    }

    public StatutTransaction getStatutTransaction() {
        return statutTransaction;
    }

    public User getCommercial() {
        return commercial;
    }

    public Card getCard() {
        return card;
    }
}
