package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumerated.TypeCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private TypeCard type;
    private String message;

    @ManyToOne
    private User demandeur;
}
