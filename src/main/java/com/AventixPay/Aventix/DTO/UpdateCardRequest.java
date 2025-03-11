package com.AventixPay.Aventix.DTO;

import com.AventixPay.Aventix.enumerated.CardStatut;
import lombok.Data;

@Data
public class UpdateCardRequest {
    private Long idEntreprise;
    private Long idUser;
    private CardStatut statut;
}
