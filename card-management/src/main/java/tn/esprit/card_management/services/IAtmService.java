package tn.esprit.card_management.services;

import tn.esprit.card_management.dto.Atmdto;
import tn.esprit.card_management.dto.Carddto;
import tn.esprit.card_management.model.Atm;
import tn.esprit.card_management.model.Card;

import java.util.List;

public interface IAtmService {
    List<Atmdto> retrieveAllAtms();

    Atm addAtm(Atmdto atmdto);

    void removeAtm(Long Id);

    Atmdto retrieveAtm(Long Id);

    Atm modifyAtm(Atmdto atmdto);

    boolean validerRetrait(String numeroCard, String codeSecurite, float montant, Atmdto atmdto);

    void effectuerRetrait(float montant, Atmdto atm);
}
