package com.mfbank.service;
import com.mfbank.dto.Notificationdto;
import com.mfbank.dto.Staticdto;
import com.mfbank.mapper.Notificationmapper;
import com.mfbank.model.Notification;
import com.mfbank.model.Static;
import com.mfbank.repository.NotificationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NotificationServiceImp implements INotificationService {


  NotificationRepository notificationRepository;
Notificationmapper notificationmapper ;
@Override
    public List<Notificationdto> retrieveAllNotifications() {
        List<Notification> notifications = notificationRepository.findAll();
        List<Notificationdto> notificationdtos = notificationmapper.notificationdtostonotifications(notifications);
        return notificationdtos;
    }
    @Override

    public Notificationdto retrieveNotification(String reference, String username) {
        Notification notification= notificationRepository.findById(reference).get();
        Notificationdto notificationdto = notificationmapper.notificationtonotificationdto(notification);
        if(username.equals(notification.getReceiver())) {
            notification.setIsRead(true);
            notificationdto.setIsRead(true);
            notificationRepository.save(notification);
        }
        return notificationdto;
    }
    @Override

    public void addNotification (Notificationdto notificationdto) {
        LocalDateTime sentTime = LocalDateTime.now();
        Notification notification= notificationmapper.notificationdtotonotification(notificationdto) ;
        notification.setSentTime(sentTime);
        notification.setIsRead(false);
        notificationRepository.save(notification);
    }


    @Override

    public void removeNotification(String reference) {
        notificationRepository.deleteById(reference);
    }
    @Override

    public Notification modifyNotification(Notificationdto notificationdto) {
        Optional<Notification> Optionalnotification = notificationRepository.findById(notificationdto.getReference());
        if(Optionalnotification.isEmpty()) {
            return null;
        }
        Notification oldNotification = Optionalnotification.get();
        Notification updatedNotification= notificationmapper.notificationdtotonotification(notificationdto);
        if(updatedNotification.getIsRead() == null) {
            updatedNotification.setIsRead(oldNotification.getIsRead());
        }
        notificationRepository.save(updatedNotification);
        return updatedNotification;
    }

}
