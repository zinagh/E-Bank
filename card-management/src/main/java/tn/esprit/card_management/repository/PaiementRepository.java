package tn.esprit.card_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.card_management.model.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement,Long> {
}
