package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumerated.CardStatut;
import javax.persistence.*;


import java.time.LocalDate;
import java.util.List;


@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique= true)
    private String cardNumber;

    @Column(nullable = false)
    private LocalDate validityDate;

    private CardStatut statut = CardStatut.ACTIVE;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "card")
    private List<Transaction> transactionList;


    public Long getId() {
        return id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDate getValidityDate() {
        return validityDate;
    }

    public CardStatut getStatut() {
        return statut;
    }

    public User getUser() {
        return user;
    }

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setValidityDate(LocalDate validityDate) {
        this.validityDate = validityDate;
    }

    public void setStatut(CardStatut statut) {
        this.statut = statut;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", validityDate=" + validityDate +
                ", statut=" + statut +
                ", user=" + user +
                ", transactionList=" + transactionList +
                '}';
    }
}

