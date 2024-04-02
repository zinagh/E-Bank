package tn.esprit.card_management.mapper;


import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.model.Card;
import java.util.List;

public interface ICardmapper {
    Card dtoToEntity(Carddto cardDto);
    Carddto entityToDto(Card card);
    List<Carddto> fromListentityTodtos (List<Card> cards);

    List<Card>fromListdtosToentities(List<Carddto> carddtos);

}
