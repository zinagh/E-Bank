package tn.esprit.card_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.card_management.model.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, String> {
}
