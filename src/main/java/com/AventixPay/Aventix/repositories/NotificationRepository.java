package com.AventixPay.Aventix.repositories;

import com.AventixPay.Aventix.entities.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByIsRead(boolean isRead);
}
