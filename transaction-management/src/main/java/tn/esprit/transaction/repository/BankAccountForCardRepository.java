package tn.esprit.transaction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transaction.model.BankAccountForCard;

@Repository
public interface BankAccountForCardRepository extends JpaRepository<BankAccountForCard, String> {
}
