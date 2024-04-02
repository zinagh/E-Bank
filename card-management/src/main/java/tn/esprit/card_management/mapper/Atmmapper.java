package tn.esprit.card_management.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Atmdto;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.model.Atm;
import tn.esprit.card_management.model.Card;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class Atmmapper  implements IAtmmapper  {


    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public Atm dtoToEntity(Atmdto atmDto) {
        return modelMapper.map(atmDto, Atm.class);
    }
    @Override
    public Atmdto entityToDto(Atm atm) {
        return modelMapper.map(atm, Atmdto.class);
    }


    @Override
    public List<Atmdto> fromListentityTodtos(List<Atm> atms) {
        return atms.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Atm> fromListdtosToentities(List<Atmdto> atmdtos) {
        return atmdtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

}
