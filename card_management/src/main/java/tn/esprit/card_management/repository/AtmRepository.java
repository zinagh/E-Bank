package tn.esprit.card_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.card_management.model.Atm;

@Repository

public interface AtmRepository extends JpaRepository<Atm, String> {
}
