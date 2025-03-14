package com.AventixPay.Aventix.DTO;


import com.AventixPay.Aventix.enumerated.CardStatut;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;



public class NewCardRequest {
    private String cardNumber;
    private Double solde;
    private CardStatut cardStatut;
    private String email;

    public String getCardNumber() {
        return cardNumber;
    }


    public CardStatut getCardStatut() {
        return cardStatut;
    }



    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public void setCardStatut(CardStatut cardStatut) {
        this.cardStatut = cardStatut;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
