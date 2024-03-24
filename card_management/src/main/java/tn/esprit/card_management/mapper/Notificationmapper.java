package tn.esprit.card_management.mapper;

import tn.esprit.card_management.dto.Atmdto;
import tn.esprit.card_management.dto.Notificationdto;
import tn.esprit.card_management.model.Notification;

import java.util.List;
import java.util.stream.Collectors;

public class Notificationmapper implements INotificationmapper {
    @Override
    public Notificationdto fromentityTodto(Notification notification) {
        Notificationdto notificationdto = new Notificationdto();
        notificationdto.setId(notification.getId());
        notificationdto.setDescription(notification.getDescription());
        notificationdto.setDate(notification.getDate());
        return notificationdto;

    }

    @Override
    public Notification fromdtoToentity(Notificationdto notificationdto) {
        Notification notification = new Notification();
        notification.setId(notificationdto.getId());
        notification.setDescription(notificationdto.getDescription());
        notification.setDate(notificationdto.getDate());
        return notification;
    }

    @Override
    public List<Notificationdto> fromListentityTodtos(List<Notification> notifications) {
        return notifications.stream()
                .map(this::fromentityTodto)
                .collect(Collectors.toList());

    }

    @Override
    public List<Notification> fromListdtosToentities(List<Notificationdto> notificationdtos) {
        return notificationdtos.stream()
                .map(this::fromdtoToentity)
                .collect(Collectors.toList());    }
}
