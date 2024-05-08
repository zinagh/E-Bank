package com.mfbank.account_managment.repository;

import com.mfbank.account_managment.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.mfbank.account_managment.model.InternationalTransfer;

import java.util.List;

@Repository
public interface InternationalTransferRepository extends JpaRepository<InternationalTransfer, Long> {

    @Query("SELECT it FROM InternationalTransfer it WHERE it.bankAccountToMakeTransfert = :bankAccount AND MONTH(it.date) = :month")
    List<InternationalTransfer> findByBankAccountAndMonth(@Param("bankAccount") BankAccount bankAccount, @Param("month") int month);

}
