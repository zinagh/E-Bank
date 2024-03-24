package tn.esprit.card_management.services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.mapper.ICardmapper;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.repository.CardRepository;

import java.util.List;
@Service
@RequiredArgsConstructor

public class CardService implements ICardService {
    private final ICardmapper iCardmapper;
    private final CardRepository cardRepository;
    @Override
    public Card addCard(Carddto carddto) {
        Card card = iCardmapper.fromdtoToentity(carddto);
        cardRepository.save(card);
        return card;

    }
@Override
    public List<Carddto> retrieveAllCards() {
        List<Card> cards = cardRepository.findAll();
        List<Carddto> carddtos = iCardmapper.fromListentityTodtos(cards);
        return carddtos;
    }


    @Override
    public void removeCard(String numeroCard ){

        cardRepository.deleteById(numeroCard);
    }
    @Override
    public Carddto retrieveCard(String numeroCard) {
        Card card = cardRepository.findById(numeroCard).get();
        return iCardmapper.fromentityTodto(card);
    }

    @Override
    public Card modifyCard(Carddto carddto) {
        Card card = iCardmapper.fromdtoToentity(carddto);
        cardRepository.save(card);
        return card;
    }




}
