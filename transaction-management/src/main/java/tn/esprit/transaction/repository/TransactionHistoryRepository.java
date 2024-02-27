package tn.esprit.transaction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transaction.model.TransactionHistory;

@Repository


public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, String> {
}
