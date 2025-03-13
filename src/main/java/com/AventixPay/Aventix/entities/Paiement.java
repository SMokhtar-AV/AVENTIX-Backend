package com.AventixPay.Aventix.entities;

import jakarta.persistence.OneToOne;

public class Paiement {

    private int id;

    @OneToOne
    private Facture facture;

    public Facture getFacture() {
        return facture;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
