package com.mfbank.controller;
import com.mfbank.dto.Notificationdto;
import com.mfbank.model.Notification;
import com.mfbank.service.INotificationService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@AllArgsConstructor
public class NotificationController {
    INotificationService notificationService;

    @GetMapping("/retrieve-all-notifications")
    public List<Notificationdto> retrieveAllNotifications() {
        List<Notificationdto> listNotificationdtos = notificationService.retrieveAllNotifications();
        return listNotificationdtos;
    }



    @GetMapping("/retrieve-notification")
    public Notificationdto retrieveNotification(
            @RequestParam String reference,
            @RequestParam String username) {
        Notificationdto ref = notificationService.retrieveNotification(reference , username);
        return ref;
    }


    @PostMapping("/add-notification")
    public void addNotification(@RequestBody Notificationdto n) {
         notificationService.addNotification(n);

    }

    @DeleteMapping("/remove-notification/{reference}")
    public void removeNotification(@PathVariable String reference) {
        notificationService.removeNotification(reference);
    }

    @PutMapping("/modify-notification")
    public Notification modifyNotification(@RequestBody Notificationdto n) {
        Notification notif = notificationService.modifyNotification(n);

        return notif;
    }
}
