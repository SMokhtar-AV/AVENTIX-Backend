package com.AventixPay.Aventix.DTO;


import com.AventixPay.Aventix.enumerated.CardStatut;
import lombok.Data;


@Data
public class NewCardRequest {
    private String cardNumber;
    private Double solde;
    private CardStatut cardStatut;
    private Long userId;
}
