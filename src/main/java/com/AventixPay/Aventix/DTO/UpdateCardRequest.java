package com.AventixPay.Aventix.DTO;

import com.AventixPay.Aventix.enumerated.CardStatut;
import lombok.Data;

@Data
public class UpdateCardRequest {
    private Long idEntreprise;
    private Long idUser;
    private CardStatut statut;


    public Long getIdEntreprise() {
        return idEntreprise;
    }

    public Long getIdUser() {
        return idUser;
    }

    public CardStatut getStatut() {
        return statut;
    }


    public void setIdEntreprise(Long idEntreprise) {
        this.idEntreprise = idEntreprise;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setStatut(CardStatut statut) {
        this.statut = statut;
    }
}
