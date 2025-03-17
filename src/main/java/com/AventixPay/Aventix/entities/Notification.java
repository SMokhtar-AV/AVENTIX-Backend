package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumClass.NotificationType;
import com.AventixPay.Aventix.enumerated.NotificationStatus;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;



@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Notification() {

    }
    public Notification(NotificationType notificationType, String s) {
        this.type = notificationType;
        this.message = s;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setDateNotification(LocalDate dateNotification) {
        this.dateNotification = dateNotification;
    }

    public void setNotificationStatus(NotificationStatus notificationStatus) {
        this.notificationStatus = notificationStatus;
    }

    public void setDemand(Demand demand) {
        this.demand = demand;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }

    public void setDestinataire(User destinataire) {
        this.destinataire = destinataire;
    }

    public Long getId() {
        return id;
    }

    public NotificationType getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public LocalDate getDateNotification() {
        return dateNotification;
    }

    public NotificationStatus getNotificationStatus() {
        return notificationStatus;
    }

    public Demand getDemand() {
        return demand;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public User getDestinataire() {
        return destinataire;
    }

    private NotificationType type;
    private String message;
    private LocalDate dateNotification;
    private NotificationStatus notificationStatus;

    @Column(name = "is_read")
    private Boolean isRead;

    public Boolean getIsRead() {
        return isRead;
    }

    public void setIsRead(boolean read) {
        isRead = read;
    }

    @OneToOne(mappedBy = "notification")
    @JsonIgnore
    private Demand demand;

    @OneToOne
    @JoinColumn(name="notification_transaction_id")
    private Transaction transaction;


    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonBackReference
    private User destinataire;

}
