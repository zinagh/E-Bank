package tn.esprit.card_management.mapper;


import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.model.Card;
import java.util.List;

public interface ICardmapper {
    Carddto fromentityTodto(Card card);
    Card fromdtoToentity(Carddto carddto);
    List<Carddto> fromListentityTodtos (List<Card> cards);

    List<Card>fromListdtosToentities(List<Carddto> carddtos);

}
