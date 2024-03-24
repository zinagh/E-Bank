package tn.esprit.card_management.mapper;


import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.model.Card;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Cardmapper implements ICardmapper {
    @Override
    public Carddto fromentityTodto(Card card) {
        Carddto carddto = new Carddto();
        carddto.setNumeroCard(card.getNumeroCard());
        carddto.setDateExpiration(card.getDateExpiration());
        carddto.setCodeSecurite(card.getCodeSecurite());
        carddto.setTitulaire(card.getTitulaire());
        carddto.setNIP(card.getNIP());
        carddto.setCVV(card.getCVV());
        carddto.setActivated(card.isActivated());
        carddto.setDisableCard(card.isDisableCard());

        carddto.setCommisionBasedOnAccount(card.getCommisionBasedOnAccount());
    return carddto;
    }
    @Override
    public Card fromdtoToentity(Carddto carddto) {
        Card card = new Card();
        card.setNumeroCard(carddto.getNumeroCard());
        card.setDateExpiration(carddto.getDateExpiration());
        card.setCodeSecurite(carddto.getCodeSecurite());
        card.setTitulaire(carddto.getTitulaire());
        card.setNIP(carddto.getNIP());
        card.setCVV(carddto.getCVV());
        card.setActivated(carddto.isActivated());
        card.setDisableCard(carddto.isDisableCard());
        card.setCommisionBasedOnAccount(carddto.getCommisionBasedOnAccount());
        return card;

    }
    @Override
    public List<Carddto> fromListentityTodtos (List<Card> cards) {
        return cards.stream()
                .map(this::fromentityTodto)
                .collect(Collectors.toList());
    }
    @Override
    public List<Card>fromListdtosToentities(List<Carddto> carddtos){
        return carddtos.stream()
                .map(this::fromdtoToentity)
                .collect(Collectors.toList());
    }

}
