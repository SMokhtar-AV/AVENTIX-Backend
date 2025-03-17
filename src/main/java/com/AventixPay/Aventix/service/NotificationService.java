package com.AventixPay.Aventix.service;


import com.AventixPay.Aventix.entities.Notification;
import com.AventixPay.Aventix.entities.Roles;
import com.AventixPay.Aventix.entities.User;
import com.AventixPay.Aventix.enumClass.Role;
import com.AventixPay.Aventix.repositories.NotificationRepository;
import com.AventixPay.Aventix.repositories.RoleRepository;
import com.AventixPay.Aventix.repositories.UserRepository;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Notification notification) {
        return notificationRepository.save(notification);
    }

    public List<Notification> getAllNotifications() {
        return notificationRepository.findAll();
    }

    public Notification getNotificationById(Long id) {
        return notificationRepository.findById(id).orElseThrow(() -> new RuntimeException("Notification not found"));
    }

    public Notification updateNotification(Long id, Notification notificationDetails) {
        Notification notification = getNotificationById(id);
        notification.setType(notificationDetails.getType());
        notification.setMessage(notificationDetails.getMessage());
        notification.setDemand(notificationDetails.getDemand());
        notification.setTransaction(notificationDetails.getTransaction());
        return notificationRepository.save(notification);
    }

    public void markNotificationAsRead(Long notificationId) {
        Notification notification = notificationRepository.findById(notificationId).orElseThrow();
        notification.setIsRead(true);
        notificationRepository.save(notification);
    }


    public void markAllAsRead(String email) {

        User user = userRepository.findByEmail(email).orElseThrow();


        List<Notification> notifications = notificationRepository.findByIsRead(false);
        Roles role = roleRepository.findByRole(Role.ADMIN_AVENTIX).get();
        if (user.getRole() == role) {

            List<Notification> notifs = notificationRepository.findAll();

            for (Notification notif : notifications) {
                notif.setIsRead(true);
            }

        }

        List<Notification> userNotifs = user.getNotifications();

        for (Notification notif : userNotifs) {
            notif.setIsRead(true);
        }

        notificationRepository.saveAll(userNotifs);
    }

    public List<Notification> getUserNotifications(String email) {

        User user = userRepository.findByEmail(email).get();

        Roles role = roleRepository.findByRole(Role.ADMIN_AVENTIX).get();
        if (user.getRole() == role) {

            List<Notification> notifs = notificationRepository.findAll();

            return notifs;
        }


        return user.getNotifications();
    }

    public void deleteNotification(Long id) {
        notificationRepository.deleteById(id);
    }
}
