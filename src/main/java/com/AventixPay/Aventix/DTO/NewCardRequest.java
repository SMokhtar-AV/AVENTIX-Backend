package com.AventixPay.Aventix.DTO;


import com.AventixPay.Aventix.enumerated.CardStatut;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;



@Builder
public class NewCardRequest {
    private String cardNumber;
    private Double solde;
    private CardStatut cardStatut;
    private Long userId;

    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public CardStatut getCardStatut() {
        return cardStatut;
    }

    public void setCardStatut(CardStatut cardStatut) {
        this.cardStatut = cardStatut;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
