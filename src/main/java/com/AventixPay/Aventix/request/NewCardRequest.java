package com.AventixPay.Aventix.request;


import com.AventixPay.Aventix.enumClass.CardStatut;
import lombok.Data;

import java.util.Date;


@Data
public class NewCardRequest {
    private String cardNumber;
    private Double solde;
    private CardStatut cardStatut;
    private Long userId;
}
