package tn.esprit.account_managment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.account_managment.model.BankAccount;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount , String> {
}
