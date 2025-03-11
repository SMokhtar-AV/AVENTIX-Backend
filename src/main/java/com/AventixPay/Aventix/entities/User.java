package com.AventixPay.Aventix.entities;

import com.AventixPay.Aventix.enumClass.Role;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String phone;
    private Double solde;

    @OneToOne(mappedBy = "user")
    private Card card;

    @ManyToOne
    private Entreprise entreprise;

    @OneToMany(mappedBy = "user")
    private List<Roles> roles;

    @OneToMany(mappedBy = "demandeur")
    private List<Demand> demandeEmises;

    @OneToMany(mappedBy = "recepteur")
    @JsonManagedReference("user-recepteur")
    private List<Demand> demandeRecues;

    @OneToMany(mappedBy = "payer")
    private List<Transaction> transactionsEffectuees;

    @OneToMany(mappedBy = "receiver")
    private List<Transaction> transactionsRecues;

    @OneToMany(mappedBy = "factureEmues")
    private List<Facture> facturesEmises;

    @OneToMany(mappedBy = "factureRecues")
    private List<Facture> facturesRecues;
}
