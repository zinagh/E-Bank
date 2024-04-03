package tn.esprit.transaction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transaction.model.Transaction;

import java.util.List;

@Repository
public interface TransactionReposiroty  extends JpaRepository<Transaction, String>, JpaSpecificationExecutor<Transaction> {
}

