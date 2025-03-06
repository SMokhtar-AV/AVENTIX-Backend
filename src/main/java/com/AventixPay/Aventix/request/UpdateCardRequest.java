package com.AventixPay.Aventix.request;

import com.AventixPay.Aventix.enumClass.CardStatut;
import lombok.Data;

@Data
public class UpdateCardRequest {
    private Long idEntreprise;
    private Long idUser;
    private CardStatut statut;
}
