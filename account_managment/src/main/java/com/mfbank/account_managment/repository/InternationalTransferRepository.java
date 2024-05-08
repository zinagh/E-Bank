package com.mfbank.account_managment.repository;

import com.mfbank.account_managment.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.mfbank.account_managment.model.InternationalTransfer;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface InternationalTransferRepository extends JpaRepository<InternationalTransfer, Long> {
    @Query("SELECT inter FROM InternationalTransfer inter  WHERE MONTH(inter.date) = ?1")
    List<InternationalTransfer> findInternationalTransferByDate( Integer date);

}
