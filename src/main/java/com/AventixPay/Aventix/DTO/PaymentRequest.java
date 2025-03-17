package com.AventixPay.Aventix.DTO;





public class PaymentRequest {
    private String cardNumber;
    private double montant;

    private int qantite;


    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getQantite() {
        return qantite;
    }

    public void setQantite(int qantite) {
        this.qantite = qantite;
    }
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
