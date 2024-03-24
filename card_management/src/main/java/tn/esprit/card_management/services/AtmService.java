package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Atmdto;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.dto.Notificationdto;
import tn.esprit.card_management.mapper.IAtmmapper;
import tn.esprit.card_management.mapper.ICardmapper;
import tn.esprit.card_management.model.Atm;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.model.Notification;
import tn.esprit.card_management.repository.AtmRepository;
import tn.esprit.card_management.repository.CardRepository;
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
        Atm atm = iAtmmapper.fromdtoToentity(atmdto);
        atmRepository.save(atm);
        return atm;
    }

    @Override
    public void removeAtm(String Id) {
        atmRepository.deleteById(Id);


    }

    @Override
    public Atmdto retrieveAtm(String Id) {
        Atm atm = atmRepository.findById(Id).get();
        return iAtmmapper.fromentityTodto(atm);
    }

    @Override
    public Atm modifyAtm(Atmdto atmdto) {
        Atm atm = iAtmmapper.fromdtoToentity(atmdto);
        atmRepository.save(atm);
        return atm;
    }
}
