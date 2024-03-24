package tn.esprit.card_management.mapper;

import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.dto.Notificationdto;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.model.Notification;

import java.util.List;

public interface INotificationmapper {
    Notificationdto fromentityTodto(Notification notification);
    Notification fromdtoToentity(Notificationdto notificationdto);
    List<Notificationdto> fromListentityTodtos (List<Notification> notifications);

    List<Notification>fromListdtosToentities(List<Notificationdto> notificationdtos);
}
