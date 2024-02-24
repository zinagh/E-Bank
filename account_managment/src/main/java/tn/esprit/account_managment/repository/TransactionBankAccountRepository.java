package tn.esprit.account_managment.repository;

import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.account_managment.model.TransactionBankAccount;

@Repository
public interface TransactionBankAccountRepository  extends JpaRepository<TransactionBankAccount , String> {
}
