package tn.esprit.card_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.card_management.model.Attachement;

public interface AttachementRepository extends JpaRepository<Attachement,Long> {
}
