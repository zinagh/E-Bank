package tn.esprit.transaction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transaction.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount , String> {
}