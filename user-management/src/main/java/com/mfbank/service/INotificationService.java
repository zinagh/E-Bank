package com.mfbank.service;
import com.mfbank.dto.Notificationdto;
import com.mfbank.model.Notification;
import java.util.List;

public interface INotificationService {
    public List<Notificationdto> retrieveAllNotifications();
    public Notificationdto retrieveNotification(String reference , String username);
    public void addNotification(Notificationdto notificationdto);
    public void removeNotification(String reference);
    public Notification modifyNotification(Notificationdto notif);
}
