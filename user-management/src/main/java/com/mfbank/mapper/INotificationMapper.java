package com.mfbank.mapper;

import com.mfbank.dto.Notificationdto;
import com.mfbank.dto.Staticdto;
import com.mfbank.dto.Userdto;
import com.mfbank.model.Notification;
import com.mfbank.model.User;

import java.util.List;

public interface INotificationMapper  {
     Notificationdto notificationtonotificationdto (Notification notification);
     Notification notificationdtotonotification (Notificationdto notificationdto);
     List<Notification> notificationsTounotificationdtos(List<Notificationdto> notificationdtos);
     List<Notificationdto> notificationdtostonotifications(List<Notification> notifications);
}
