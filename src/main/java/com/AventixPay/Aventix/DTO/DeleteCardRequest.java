package com.AventixPay.Aventix.DTO;


import lombok.Builder;
import lombok.Data;


@Builder
public class DeleteCardRequest {
    private Long cardId;

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }
}
