package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Atmdto;
import tn.esprit.card_management.mapper.IAtmmapper;
import tn.esprit.card_management.model.Atm;
import tn.esprit.card_management.repository.AtmRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AtmService implements IAtmService {
    private final IAtmmapper iAtmmapper;
    private final AtmRepository atmRepository;
    @Override
    public List<Atmdto> retrieveAllAtms() {
        List<Atm> atms = atmRepository.findAll();
        List<Atmdto> atmdtos = iAtmmapper.fromListentityTodtos(atms);
        return atmdtos;    }

    @Override
    public Atm addAtm(Atmdto atmdto) {
        Atm atm = iAtmmapper.dtoToEntity(atmdto);
        atmRepository.save(atm);
        return atm;
    }

    @Override
    public void removeAtm(Long Id) {
        atmRepository.deleteById(Id);


    }

    @Override
    public Atmdto retrieveAtm(Long Id) {
        Atm atm = atmRepository.findById(Id).get();
        return iAtmmapper.entityToDto(atm);
    }

    @Override
    public Atm modifyAtm(Atmdto atmdto) {
        Atm atm = iAtmmapper.dtoToEntity(atmdto);
        atmRepository.save(atm);
        return atm;
    }
}
