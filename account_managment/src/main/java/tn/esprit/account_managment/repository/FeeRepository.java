package tn.esprit.account_managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.account_managment.model.Fee;
import tn.esprit.account_managment.model.FeeType;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
    List<Fee> findByFeeType(FeeType feeType);
}
