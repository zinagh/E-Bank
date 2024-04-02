package tn.esprit.card_management.mapper;

import tn.esprit.card_management.dto.Reclamationdto;
import tn.esprit.card_management.model.Reclamation;

import java.util.List;
import java.util.stream.Collectors;

public interface IReclamationmapper {
     Reclamation dtoToEntity(Reclamationdto reclamationdto);

     Reclamationdto entityToDto(Reclamation reclamation);


     List<Reclamationdto> fromListentityTodtos(List<Reclamation> reclamations);

     List<Reclamation> fromListdtosToentities(List<Reclamationdto> reclamationdtos);

}
