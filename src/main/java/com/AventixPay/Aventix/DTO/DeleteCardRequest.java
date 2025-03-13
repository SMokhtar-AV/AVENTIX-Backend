package com.AventixPay.Aventix.DTO;


import lombok.Data;

@Data
public class DeleteCardRequest {
    private Long cardId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
