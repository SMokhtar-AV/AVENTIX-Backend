package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumerated.CardStatut;
import jakarta.persistence.*;
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
}
