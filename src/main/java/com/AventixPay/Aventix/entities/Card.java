package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumClass.CardStatut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique= true)
    private String cardNumber;

    @Column(nullable = false)
    private LocalDate validityDate;

    private Double solde = 0.0;

    private CardStatut statut = CardStatut.ACTIVE;

    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    @OneToMany
    private List<Transaction> transactionList;
}
