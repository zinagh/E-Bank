package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Atmdto;
import tn.esprit.card_management.mapper.IAtmmapper;
import tn.esprit.card_management.model.Atm;
import tn.esprit.card_management.model.Card;
import tn.esprit.card_management.repository.AtmRepository;
import tn.esprit.card_management.repository.CardRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AtmService implements IAtmService {
    private final IAtmmapper iAtmmapper;
    private final AtmRepository atmRepository;
    private final CardRepository cardRepository;

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
    @Override
    public boolean validerRetrait(String numeroCard, String codeSecurite, float montant, Atmdto atmdto) {

        if (!atmdto.isActivated()) {
            return false;
        }
        if (montant <= 0) {
            return false;
        }
        if (montant > atmdto.getSomme()) {
            return false;
        }
        Optional<Card> Optionalcard = cardRepository.findById(numeroCard);
        if(Optionalcard.isEmpty()){
            return false;
        } else {
            Card card = Optionalcard.get();
            if(card.getCodeSecurite().equals(codeSecurite)){
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public void effectuerRetrait(float montant, Atmdto atmdto) {

        // Débiter le montant du solde de l'ATM
        atmdto.setSomme(atmdto.getSomme() - montant);


    }

}


