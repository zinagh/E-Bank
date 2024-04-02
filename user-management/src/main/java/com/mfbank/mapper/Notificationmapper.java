package com.mfbank.mapper;
import com.mfbank.dto.Notificationdto;
import com.mfbank.model.Notification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class Notificationmapper  implements INotificationMapper {
    public Notificationdto notificationtonotificationdto (Notification notification) {
        Notificationdto notificationdto = new Notificationdto();
        notificationdto.setReference(notification.getReference());
        notificationdto.setSender(notification.getSender());
        notificationdto.setReceiver(notification.getReceiver());
        notificationdto.setSentTime(notification.getSentTime());
        notificationdto.setIsRead(notification.getIsRead());
        notificationdto.setType(notification.getType());

        return notificationdto;
    }
    public Notification notificationdtotonotification (Notificationdto notificationdto) {
        Notification notification = new Notification();
        notification.setReference(notificationdto.getReference());
        notification.setSender(notificationdto.getSender());
        notification.setReceiver(notificationdto.getReceiver());
        notification.setSentTime(notificationdto.getSentTime());
        notification.setIsRead(notificationdto.getIsRead());
        notification.setType(notificationdto.getType());
        return notification;
    }
    @Override
   public List<Notification> notificationsTounotificationdtos (List<Notificationdto> notificationdtos){
        return notificationdtos.stream()
                .map(this::notificationdtotonotification)
                .collect(Collectors.toList());

    }

    @Override
   public List<Notificationdto> notificationdtostonotifications(List<Notification> notifications){
        return  notifications.stream()
                .map(this::notificationtonotificationdto)
                .collect(Collectors.toList());
    }
}
