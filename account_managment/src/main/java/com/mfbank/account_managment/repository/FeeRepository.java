package com.mfbank.account_managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mfbank.account_managment.model.Fee;
import com.mfbank.account_managment.model.FeeType;

import java.util.List;

@Repository
public interface FeeRepository extends JpaRepository<Fee, Long> {
    List<Fee> findByFeeType(FeeType feeType);
}
