package tn.esprit.card_management.mapper;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.model.Card;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Cardmapper implements ICardmapper {

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public Card dtoToEntity(Carddto cardDto) {
        return modelMapper.map(cardDto, Card.class);
    }
    @Override
    public Carddto entityToDto(Card card) {
        return modelMapper.map(card, Carddto.class);
    }

    @Override
    public List<Carddto> fromListentityTodtos (List<Card> cards) {
        return cards.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }
    @Override
    public List<Card>fromListdtosToentities(List<Carddto> carddtos){
        return carddtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

}
