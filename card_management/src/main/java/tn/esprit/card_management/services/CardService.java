package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.mapper.ICardmapper;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.repository.CardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CardService {
    private final ICardmapper iCardmapper;
    private final CardRepository cardRepository;
    public void addCard(Carddto carddto) {
        Card card = iCardmapper.fromdtoToentity(carddto);
        cardRepository.save(card);
    }

    public List<Carddto> retrieveall() {
        List<Card> cards = cardRepository.findAll();
        List<Carddto> carddtos = iCardmapper.fromListentityTodtos(cards);
        return carddtos;
    }
}
