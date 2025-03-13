package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumClass.DemandType;
import com.AventixPay.Aventix.enumClass.DemandeEtat;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;




@Entity
public class Demand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private DemandType type;
    private String message;
    private String description;
    private Date date;

    @ManyToOne
    @JoinColumn(name="id_user_demande")
    @JsonBackReference("user-demand")
    private User user;

    @OneToOne
    @JoinColumn(name="demande_notification")
    private Notification notification;

    private DemandeEtat etat;


    public Long getId() {
        return id;
    }

    public DemandType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public Notification getNotification() {
        return notification;
    }

    public DemandeEtat getEtat() {
        return etat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(DemandType type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public void setEtat(DemandeEtat etat) {
        this.etat = etat;
    }
}

