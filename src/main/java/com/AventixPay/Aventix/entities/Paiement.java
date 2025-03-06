package com.AventixPay.Aventix.entities;

import jakarta.persistence.OneToOne;

public class Paiement {

    private int id;



    @OneToOne
    private Facture facture;
}
