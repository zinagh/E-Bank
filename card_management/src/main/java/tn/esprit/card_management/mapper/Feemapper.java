package tn.esprit.card_management.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Atmdto;
import tn.esprit.card_management.dto.Feedto;
import tn.esprit.card_management.model.Atm;
import tn.esprit.card_management.model.Fee;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Feemapper implements IFeemapper{
    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public Fee dtoToEntity(Feedto feedto) {
        return modelMapper.map(feedto, Fee.class);
    }
    @Override
    public Feedto entityToDto(Fee fee) {
        return modelMapper.map(fee, Feedto.class);
    }


    @Override
    public List<Feedto> fromListentityTodtos(List<Fee> fees) {
        return fees.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Fee> fromListdtosToentities(List<Feedto> feedtos) {
        return feedtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

}
