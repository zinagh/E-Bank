package com.mfbank.account_managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mfbank.account_managment.model.InternationalTransfer;

@Repository
public interface InternationalTransferRepository extends JpaRepository<InternationalTransfer, Long> {
}
