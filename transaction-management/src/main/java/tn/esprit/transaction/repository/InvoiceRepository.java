package tn.esprit.transaction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transaction.model.Invoice;
import tn.esprit.transaction.model.Transaction;

import java.util.Optional;

@Repository

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
    Optional<Invoice> findByTransaction(Transaction transaction);
}
