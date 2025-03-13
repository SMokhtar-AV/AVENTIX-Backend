package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumerated.StatutTransaction;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double montant;

    private LocalDateTime dateTransaction;

    private StatutTransaction statutTransaction;

    @ManyToOne
    private User commercial;

    @ManyToOne
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
