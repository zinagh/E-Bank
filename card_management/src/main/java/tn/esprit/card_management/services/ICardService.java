package tn.esprit.card_management.services;

import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.model.Card;

import java.util.List;

public interface ICardService {
    List<Carddto> retrieveAllCards();
    Card demandeCard(Carddto carddto);
     void removeCard(String numeroCard);
    Carddto retrieveCard(String numeroCard);
    Card modifyCard(Carddto carddto);
    Card activateCard(String id);
}
