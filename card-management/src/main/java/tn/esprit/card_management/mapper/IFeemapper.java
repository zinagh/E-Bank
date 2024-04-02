package tn.esprit.card_management.mapper;

import tn.esprit.card_management.dto.Feedto;
import tn.esprit.card_management.model.Fee;

import java.util.List;
import java.util.stream.Collectors;

public interface IFeemapper {
     Fee dtoToEntity(Feedto feedto);
     Feedto entityToDto(Fee fee);


     List<Feedto> fromListentityTodtos(List<Fee> fees);
     List<Fee> fromListdtosToentities(List<Feedto> feedtos);

}
