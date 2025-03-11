package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumClass.DemandType;
import com.AventixPay.Aventix.enumClass.DemandeEtat;
import com.AventixPay.Aventix.enumClass.TypeCard;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DemandType type;
    private String message;
    private String description;
    private Date date;

    @ManyToOne
    @JoinColumn(name="id_user_demandeur")
    @JsonBackReference("user-demandeur")
    private User demandeur;

    @ManyToOne
    @JoinColumn(name="id_user_recepteur")
    @JsonBackReference("user-recepteur")
    private User recepteur;

    @OneToOne
    @JoinColumn(name="demande_notification")
    private Notification notification;

    private DemandeEtat etat;


}
