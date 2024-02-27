package tn.esprit.transaction.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.transaction.model.UserAsStudentonBA;
@Repository
public interface UserAsStudentonBARepository extends JpaRepository<UserAsStudentonBA, String> {
}
