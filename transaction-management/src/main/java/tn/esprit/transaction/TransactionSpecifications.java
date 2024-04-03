package tn.esprit.transaction;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import tn.esprit.transaction.model.Transaction;

public class TransactionSpecifications {
        public static Specification<Transaction> keywordSearch(String keyword) {
            return (Root<Transaction> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
                // Créer une prédicat qui recherche dans tous les champs de Transaction
                return criteriaBuilder.or(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("reference")), "%" + keyword.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("type")), "%" + keyword.toLowerCase() + "%"),
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), "%" + keyword.toLowerCase() + "%")

                        // Ajoutez d'autres champs ici si nécessaire
                );
            };
        }
    }

