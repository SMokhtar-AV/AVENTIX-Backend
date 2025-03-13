package com.AventixPay.Aventix.entities;

import com.AventixPay.Aventix.enumClass.Role;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;



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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public void setCard(Card card) {
        this.card = card;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    public void setDemandeRecues(List<Demand> demandeRecues) {
        this.demandeRecues = demandeRecues;
    }

    public void setTransactionsRecues(List<Transaction> transactionsRecues) {
        this.transactionsRecues = transactionsRecues;
    }

    public void setFacturesEmises(List<Facture> facturesEmises) {
        this.facturesEmises = facturesEmises;
    }



    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public Double getSolde() {
        return solde;
    }

    public Card getCard() {
        return card;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public Roles getRole() {
        return role;
    }

    public List<Demand> getDemandeRecues() {
        return demandeRecues;
    }

    public List<Transaction> getTransactionsRecues() {
        return transactionsRecues;
    }

    public List<Facture> getFacturesEmises() {
        return facturesEmises;
    }



    @OneToOne(mappedBy = "user")
    @JsonIgnore
    private Card card;

    @ManyToOne
    @JsonBackReference("user-entreprise")
    private Entreprise entreprise;

    @ManyToOne
    @JsonBackReference
    private Roles role;


    @OneToMany(mappedBy = "user")
    @JsonManagedReference("user-demand")
    private List<Demand> demandeRecues;



    @OneToMany(mappedBy = "commercial")

    private List<Transaction> transactionsRecues;

    @OneToMany(mappedBy = "user")
    private List<Facture> facturesEmises;


}
