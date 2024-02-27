package tn.esprit.transaction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transaction.model.Transaction;

@Repository
public interface TransactionReposiroty  extends JpaRepository<Transaction, String> {
}
