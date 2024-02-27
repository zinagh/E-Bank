package tn.esprit.transaction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transaction.model.Invoice;

@Repository

public interface InvoiceRepository extends JpaRepository<Invoice, String> {
}
