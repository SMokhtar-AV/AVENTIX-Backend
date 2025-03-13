package com.AventixPay.Aventix.DTO;


import lombok.Builder;
import lombok.Data;


@Builder
public class PaymentRequest {
    private String cardNumber;
    private double montant;

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
