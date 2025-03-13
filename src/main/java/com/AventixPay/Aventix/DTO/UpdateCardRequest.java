package com.AventixPay.Aventix.DTO;

import com.AventixPay.Aventix.enumerated.CardStatut;
import lombok.Builder;
import lombok.Data;


@Builder
public class UpdateCardRequest {
    private Long idEntreprise;
    private Long idUser;
    private CardStatut statut;

    public Long getIdEntreprise() {
        return idEntreprise;
    }

    public void setIdEntreprise(Long idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public CardStatut getStatut() {
        return statut;
    }

    public void setStatut(CardStatut statut) {
        this.statut = statut;
    }
}
