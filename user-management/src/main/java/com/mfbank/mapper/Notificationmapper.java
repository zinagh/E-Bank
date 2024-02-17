package com.mfbank.mapper;

import com.mfbank.dto.Notificationdto;

import com.mfbank.model.Notification;

public class Notificationmapper {
    public Notificationdto notificationtonotificationdto (Notification notification) {
        Notificationdto notificationdto = new Notificationdto();
        notificationdto.setReference(notification.getReference());
        notificationdto.setSender(notification.getSender());
        notificationdto.setReceiver(notification.getReceiver());
        notificationdto.setSentTime(notification.getSentTime());
        notificationdto.setIsRead(notification.getIsRead());
        return notificationdto;
    }
    public Notification notificationdtotonotification (Notificationdto notificationdto) {
        Notification notification = new Notification();
        notification.setReference(notificationdto.getReference());
        notification.setSender(notificationdto.getSender());
        notification.setReceiver(notificationdto.getReceiver());
        notification.setSentTime(notificationdto.getSentTime());
        notification.setIsRead(notificationdto.getIsRead());
        return notification;
    }
}
