package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumerated.StatutTransaction;
import jakarta.persistence.*;
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

}
