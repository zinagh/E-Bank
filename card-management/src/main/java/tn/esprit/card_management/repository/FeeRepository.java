package tn.esprit.card_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.card_management.model.Fee;
import tn.esprit.card_management.model.FeeType;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
    List<Fee> findByType(FeeType feeType);
}
