package tn.esprit.transaction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transaction.model.TransactionBankAccount;

@Repository
public interface TransactionBankAccountRepository extends JpaRepository<TransactionBankAccount, String> {
}
