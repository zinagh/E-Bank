package tn.esprit.card_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.card_management.model.Notification;

@Repository

public interface NotificationRepository extends JpaRepository<Notification,String> {
}
