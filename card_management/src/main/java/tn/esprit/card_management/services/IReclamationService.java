 package tn.esprit.card_management.services;


import tn.esprit.card_management.dto.Reclamationdto;
import tn.esprit.card_management.model.Reclamation;

import java.util.List;

public interface IReclamationService {
    List<Reclamationdto> retrieveAllReclamations();
    Reclamation addReclamation(Reclamationdto reclamationdto);
    void removeReclamation(Long Id);
    Reclamationdto retrieveReclamation(Long Id);
    Reclamation modifyReclamation(Reclamationdto reclamationdto);
}
