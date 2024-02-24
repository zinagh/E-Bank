package tn.esprit.account_managment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.account_managment.model.UserAsStudentonBA;

@Repository
public interface UserAsStudentonBARepository extends JpaRepository <UserAsStudentonBA , String> {
}
