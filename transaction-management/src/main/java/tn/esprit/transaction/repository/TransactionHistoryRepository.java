package tn.esprit.transaction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transaction.model.Transaction;
import tn.esprit.transaction.model.TransactionHistory;

import java.util.List;

@Repository


public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, String> {

}
