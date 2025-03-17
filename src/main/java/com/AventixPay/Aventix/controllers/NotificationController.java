package com.AventixPay.Aventix.controllers;


import com.AventixPay.Aventix.entities.Notification;
import com.AventixPay.Aventix.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@CrossOrigin("*")
public class NotificationController {

    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.createNotification(notification));
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getAllNotifications() {
        return ResponseEntity.ok(notificationService.getAllNotifications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotificationById(@PathVariable Long id) {
        return ResponseEntity.ok(notificationService.getNotificationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        return ResponseEntity.ok(notificationService.updateNotification(id, notification));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNotification(@PathVariable Long id) {
        notificationService.deleteNotification(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{notificationId}/markAsRead")
    public ResponseEntity<Void> markAsRead(@PathVariable Long notificationId) {
        notificationService.markNotificationAsRead(notificationId);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/user/{email}")
    public ResponseEntity<List<Notification>> getUserNotifications(@PathVariable String email) {
        return ResponseEntity.ok(notificationService.getUserNotifications(email));

    }


    @PutMapping("/read-all")
    public ResponseEntity<Void> markAllAsRead(@RequestBody String email) {
        notificationService.markAllAsRead(email);
        return ResponseEntity.ok().build();
    }


}
