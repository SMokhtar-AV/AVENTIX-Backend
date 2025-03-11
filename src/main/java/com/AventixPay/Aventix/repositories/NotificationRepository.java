package com.AventixPay.Aventix.repositories;

import com.AventixPay.Aventix.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
