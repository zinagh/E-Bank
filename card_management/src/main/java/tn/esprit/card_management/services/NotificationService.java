package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.dto.Notificationdto;
import tn.esprit.card_management.mapper.INotificationmapper;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.model.Notification;

import tn.esprit.card_management.repository.NotificationRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService implements INotificationService {
    private final INotificationmapper iNotificationmapper;
    private final NotificationRepository notificationRepository;

    @Override
    public List<Notificationdto> retrieveAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        List<Notificationdto> notificationdtos = iNotificationmapper.fromListentityTodtos(notifications);
        return notificationdtos;
    }

    @Override
    public Notification addNotification(Notificationdto notificationdto) {
        Notification notification = iNotificationmapper.fromdtoToentity(notificationdto);
        notificationRepository.save(notification);
        return notification;
    }

    @Override
    public void removeNotification(String Id) {

        notificationRepository.deleteById(Id);    }

    @Override
    public Notificationdto retrieveNotification(String Id) {
        Notification notification = notificationRepository.findById(Id).get();
        return iNotificationmapper.fromentityTodto(notification);
    }

    @Override
    public Notification modifyNotification(Notificationdto notificationdto) {
        Notification notification = iNotificationmapper.fromdtoToentity(notificationdto);
        notificationRepository.save(notification);
        return notification;
    }
}
