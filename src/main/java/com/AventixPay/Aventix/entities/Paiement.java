package com.AventixPay.Aventix.entities;

import javax.persistence.*;

@Entity
public class Paiement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public int getId() {
        return id;
    }

    public Facture getFacture() {
        return facture;
    }

    @OneToOne
    private Facture facture;
}
