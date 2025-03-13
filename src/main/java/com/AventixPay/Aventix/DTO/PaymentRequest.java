package com.AventixPay.Aventix.DTO;





public class PaymentRequest {
    private String cardNumber;
    private double montant;

    public String getCardNumber() {
        return cardNumber;
    }

    public double getMontant() {
        return montant;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }
}
