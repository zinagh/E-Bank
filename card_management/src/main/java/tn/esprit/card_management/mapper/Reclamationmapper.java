package tn.esprit.card_management.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Reclamationdto;
import tn.esprit.card_management.model.Reclamation;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class Reclamationmapper implements IReclamationmapper {
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public Reclamation dtoToEntity(Reclamationdto reclamationdto) {
        return modelMapper.map(reclamationdto, Reclamation.class);
    }
    @Override
    public Reclamationdto entityToDto(Reclamation reclamation) {
        return modelMapper.map(reclamation, Reclamationdto.class);
    }



    @Override
    public List<Reclamationdto> fromListentityTodtos(List<Reclamation> reclamations) {
        return reclamations.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());

    }

    @Override
    public List<Reclamation> fromListdtosToentities(List<Reclamationdto> reclamationdtos) {
        return reclamationdtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());    }
}
