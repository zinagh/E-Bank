package tn.esprit.card_management.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.dto.Notificationdto;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.model.Notification;
import tn.esprit.card_management.services.INotificationService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/notification")
public class NotificationController {
    INotificationService iNotificationService;

    @GetMapping("retrieve-all-notification")
    public List<Notificationdto> getNotifications() {
        List<Notificationdto> listNotifications = iNotificationService.retrieveAllNotifications();
        return listNotifications;
    }

    @PostMapping("/add-notification")
    public Notification addNotification(@RequestBody Notificationdto n) {
        Notification notification = iNotificationService.addNotification(n);
        return notification;
    }

    @DeleteMapping("/remove-notification/{id}")
    public void removenotif(@PathVariable("id") String id) {
        iNotificationService.removeNotification(id);

    }
    @GetMapping("/retrieve-atm/{id}")
    public ResponseEntity<Notificationdto> getNotif(@PathVariable("id") String id) {
        try {
            Notificationdto notificationdto = iNotificationService.retrieveNotification(id);
            if (notificationdto != null) {
                return new ResponseEntity<>(notificationdto, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PutMapping("/modify-notification")
    public Notification updateNotif(@RequestBody Notificationdto n){
        Notification notification = iNotificationService.modifyNotification(n);
        return notification;
    }
}
