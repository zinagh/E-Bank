package tn.esprit.card_management.services;

import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.dto.Notificationdto;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.model.Notification;

import java.util.List;

public interface INotificationService {
    List<Notificationdto> retrieveAllNotifications();
    Notification addNotification(Notificationdto notificationdto);
    void removeNotification(String Id);
    Notificationdto retrieveNotification(String Id);
    Notification modifyNotification(Notificationdto notificationdto);
}
