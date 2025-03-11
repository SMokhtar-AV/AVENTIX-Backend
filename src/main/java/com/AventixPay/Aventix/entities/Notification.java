package com.AventixPay.Aventix.entities;


import com.AventixPay.Aventix.enumClass.NotificationType;
import com.AventixPay.Aventix.enumClass.TypeCard;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private NotificationType type;
    private String message;
    private LocalDate dateNotification;

    @OneToOne(mappedBy = "notification")
    private Demand demand;

    @OneToOne
    @JoinColumn(name="notification_transaction_id")
    private Transaction transaction;


    @ManyToOne
    @JoinColumn(name="user_id")
    private User destinataire;
}
