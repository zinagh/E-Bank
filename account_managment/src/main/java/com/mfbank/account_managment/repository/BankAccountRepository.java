package com.mfbank.account_managment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mfbank.account_managment.model.BankAccount;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends JpaRepository<BankAccount , String> {
    @Query("SELECT ba FROM BankAccount ba JOIN ba.internationalTransfers i WHERE i.id = ?1")
    Optional<BankAccount> findByInternationalTransferId(Long internationalTransferId);

    Optional<BankAccount> findByTitulaire(String titulaire);
}
